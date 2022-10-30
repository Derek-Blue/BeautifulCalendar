package com.farris.beauty.time.sdjdi.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.beauty.time.sdjdi.module.usecase.forecast.singleelement.SingleElementForecastUseCase
import com.farris.beauty.time.sdjdi.module.usecase.forecast.singleelement.SingleElementForecastUseCaseImpl
import com.farris.beauty.time.sdjdi.screen.sample.SampleItem
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.SAMPLE_FORMAT
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: SingleElementForecastUseCase = SingleElementForecastUseCaseImpl()
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    private val currentState get() = _viewState.value
    val viewState = _viewState.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val elementFlow = MutableSharedFlow<WeatherElementType>()

    init {
        viewModelScope.launch {
            elementFlow.onEach {
                _viewState.update {
                    currentState.copy(isProgress = true)
                }
            }.map { element ->
                val data = useCase(CountyType.Taoyuan, CycleType.TreeDays, element).getOrThrow()
                    .map { entry ->
                        entry.value.map { elementTime ->
                            SampleItem(
                                townShipName = elementTime.townShip,
                                element = elementTime.elementName,
                                start = SAMPLE_FORMAT.format(elementTime.startTime.time),
                                end = elementTime.endTime?.let {
                                    SAMPLE_FORMAT.format(it.time)
                                } ?: "-",
                                valueText = elementTime.weatherElements.toString()
                            )
                        }
                    }.flatten()

                currentState.copy(items = data)
            }
                .catch {
                    _error.emit(it.message ?: "unknown error")
                    _viewState.update {
                        currentState.copy(isProgress = false)
                    }
                }
                .collectLatest { newState ->
                    _viewState.update {
                        newState.copy(isProgress = false)
                    }
                }
        }
    }

    fun setElement(weatherElementType: WeatherElementType) {
        viewModelScope.launch {
            elementFlow.emit(weatherElementType)
        }
    }
}
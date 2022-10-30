package com.farris.beauty.time.sdjdi.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.beauty.time.sdjdi.module.usecase.forecast.ForecastUseCase
import com.farris.beauty.time.sdjdi.module.usecase.forecast.ForecastUseCaseImpl
import com.farris.beauty.time.sdjdi.screen.sample.SampleItem
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.SAMPLE_FORMAT
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: ForecastUseCase = ForecastUseCaseImpl()
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
                val data = useCase(
                    CountyType.Yilan,
                    CycleType.Week,
                    listOf(element)
                ).getOrThrow().map { entry ->
                    val townShip = entry.key
                    entry.value.map { elementEntry ->
                        val elementName = elementEntry.key.elementName
                        elementEntry.value.map { time ->
                            SampleItem(
                                townShipName = townShip,
                                element = elementName,
                                start = SAMPLE_FORMAT.format(time.startTime.time),
                                end = time.endTime?.let {
                                    SAMPLE_FORMAT.format(it.time)
                                } ?: "-",
                                valueText = time.weatherElements.toString()
                            )
                        }
                    }.flatten()
                }.flatten()

                currentState.copy(items = data)
            }
                .catch {
                    _error.emit(it.message ?: "unknown error")
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
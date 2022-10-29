package com.farris.beauty.time.sdjdi.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.beauty.time.sdjdi.module.repository.forecast.ForecastRepository
import com.farris.beauty.time.sdjdi.module.repository.forecast.ThreeDaysForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.type.CountyType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val threeDaysForecastRepository: ForecastRepository = ThreeDaysForecastRepositoryImpl()
): ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    private val currentState get() = _viewState.value
    val viewState = _viewState.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    init {
        viewModelScope.launch {
            flow{
                val data = threeDaysForecastRepository.getData(CountyType.Taoyuan)
                    .getOrThrow()
                emit(currentState.copy(repositoryData = data))
            }
//                .catch {
//                    _error.emit(it.message ?: "repository error")
//                }
                .collectLatest { newState ->
                    _viewState.update {
                        newState.copy(isProgress = false)
                    }
                }
        }
    }
}
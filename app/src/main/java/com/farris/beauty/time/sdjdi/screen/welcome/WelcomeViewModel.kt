package com.farris.beauty.time.sdjdi.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.beauty.time.sdjdi.module.remoteconfig.FireBaseRemoteConfigImpl
import com.farris.beauty.time.sdjdi.module.remoteconfig.RemoteConfig
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val remoteConfig: RemoteConfig = FireBaseRemoteConfigImpl()
) : ViewModel() {

    private val _finishInitFlow = MutableSharedFlow<Unit>()
    val finishInitFlow = _finishInitFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            remoteConfig.init().fold({
                _finishInitFlow.emit(Unit)
            }, {
                _finishInitFlow.emit(Unit)
            })
        }
    }
}
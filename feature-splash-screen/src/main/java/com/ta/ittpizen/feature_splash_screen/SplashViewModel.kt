package com.ta.ittpizen.feature_splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val settingUseCase: SettingPreferenceUseCase
) : ViewModel() {

    val isFirstOpen: Flow<Boolean> = settingUseCase.isFirstOpen

    val isLogin: Flow<Boolean> = settingUseCase.isLogging

    fun updateFirstOpenState(state: Boolean) {
        viewModelScope.launch {
            settingUseCase.updateFirstOpenState(state)
        }
    }

}
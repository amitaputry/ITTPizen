package com.ta.ittpizen.feature_onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val settingUseCase: SettingPreferenceUseCase
) : ViewModel() {
    fun updateFirstOpenState(state: Boolean) {
        viewModelScope.launch {
            settingUseCase.updateFirstOpenState(state)
        }
    }

}
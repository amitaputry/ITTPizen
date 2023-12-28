package com.ta.ittpizen.feature_job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddJobGeneralViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddJobGeneralUiState())
    val uiState: StateFlow<AddJobGeneralUiState> get() = _uiState

    val jobTitleError get() = _uiState.map {
        it.jobTitleErrorMessage.isNotEmpty()
    }
    val companyNameError get() = _uiState.map {
        it.companyNameErrorMessage.isNotEmpty()
    }
    val streetError get() = _uiState.map {
        it.streetErrorMessage.isNotEmpty()
    }
    val cityError get() = _uiState.map {
        it.cityErrorMessage.isNotEmpty()
    }
    val provinceError get() = _uiState.map {
        it.provinceErrorMessage.isNotEmpty()
    }

    val buttonNextGeneralEnabled get() = _uiState.map {
        it.jobTitle.isNotEmpty() && it.companyName.isNotEmpty() && it.street.isNotEmpty() &&
        it.city.isNotEmpty() && it.province.isNotEmpty() && it.workplaceType.isNotEmpty() && it.jobType.isNotEmpty()
    }

    fun updateJobTitle(jobTitle: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(jobTitle = jobTitle)
            }
        }
    }
    fun updateCompanyName(companyName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(companyName = companyName)
            }
        }
    }
    fun updateStreet(street: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(street = street)
            }
        }
    }
    fun updateCity(city: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(city = city)
            }
        }
    }
    fun updateProvince(province: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(province = province)
            }
        }
    }
    fun updateWorkplaceType(workplaceType: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(workplaceType = workplaceType)
            }
        }
    }
    fun updateJobType(jobType: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(jobType = jobType)
            }
        }
    }

    fun updateJobTitleErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(jobTitleErrorMessage = message)
            }
        }
    }
    fun updateCompanyNameErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(companyNameErrorMessage = message)
            }
        }
    }
    fun updateStreetErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(streetErrorMessage = message)
            }
        }
    }
    fun updateCityErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(cityErrorMessage = message)
            }
        }
    }
    fun updateProvinceErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(provinceErrorMessage = message)
            }
        }
    }
}
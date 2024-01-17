package com.ta.ittpizen.feature_profile.di

import com.ta.ittpizen.feature_profile.edit.EditProfileViewModel
import com.ta.ittpizen.feature_profile.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get(), get(), get(), get()) }
    viewModel { EditProfileViewModel() }
}

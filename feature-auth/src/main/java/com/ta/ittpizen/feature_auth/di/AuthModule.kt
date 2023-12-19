package com.ta.ittpizen.feature_auth.di

import com.ta.ittpizen.feature_auth.login.LoginViewModel
import com.ta.ittpizen.feature_auth.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
}
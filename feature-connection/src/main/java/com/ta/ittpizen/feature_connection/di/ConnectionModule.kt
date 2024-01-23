package com.ta.ittpizen.feature_connection.di

import com.ta.ittpizen.feature_connection.connection.ConnectionViewModel
import com.ta.ittpizen.feature_connection.search.SearchConnectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val connectionModule = module {
    viewModel { ConnectionViewModel(get(), get(), get()) }
    viewModel { SearchConnectionViewModel(get(), get(), get()) }
}

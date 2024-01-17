package com.ta.ittpizen.feature_job.di

import com.ta.ittpizen.feature_job.add.AddJobDetailViewModel
import com.ta.ittpizen.feature_job.add.AddJobGeneralViewModel
import com.ta.ittpizen.feature_job.job.JobViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jobModule = module {
    viewModel { AddJobGeneralViewModel() }
    viewModel { AddJobDetailViewModel() }
    viewModel { JobViewModel(get(), get(), get()) }
}
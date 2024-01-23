package com.ta.ittpizen.feature_job.di

import com.ta.ittpizen.feature_job.add.AddJobDetailViewModel
import com.ta.ittpizen.feature_job.add.AddJobGeneralViewModel
import com.ta.ittpizen.feature_job.add.AddJobViewModel
import com.ta.ittpizen.feature_job.detail.JobDetailViewModel
import com.ta.ittpizen.feature_job.job.JobViewModel
import com.ta.ittpizen.feature_job.saved.SavedJobViewModel
import com.ta.ittpizen.feature_job.search.SearchJobViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jobModule = module {
    viewModel { AddJobGeneralViewModel() }
    viewModel { AddJobDetailViewModel() }
    viewModel { AddJobViewModel(get(), get()) }
    viewModel { JobViewModel(get(), get(), get()) }
    viewModel { JobDetailViewModel(get(), get()) }
    viewModel { SavedJobViewModel(get(), get(), get()) }
    viewModel { SearchJobViewModel(get(), get(), get()) }
}
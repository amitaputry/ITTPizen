package com.ta.ittpizen.feature_post.di

import com.ta.ittpizen.feature_post.add.AddPostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postModule = module {
    viewModel { AddPostViewModel(get(), get()) }
}

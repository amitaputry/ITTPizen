package com.ta.ittpizen.domain.di

import com.ta.ittpizen.domain.interactor.IttpizenInteractor
import com.ta.ittpizen.domain.interactor.PagedIttpizenInteractor
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.PagedIttpizenUseCase
import org.koin.dsl.module

val domainModule = module {
    single<IttpizenUseCase> { IttpizenInteractor(get()) }
    single<PagedIttpizenUseCase> { PagedIttpizenInteractor(get()) }
}

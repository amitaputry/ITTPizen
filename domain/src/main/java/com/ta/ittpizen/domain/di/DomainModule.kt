package com.ta.ittpizen.domain.di

import com.ta.ittpizen.domain.interactor.IttpizenInteractor
import com.ta.ittpizen.domain.interactor.IttpizenPagedInteractor
import com.ta.ittpizen.domain.interactor.SettingPreferenceInteractor
import com.ta.ittpizen.domain.interactor.UserPreferenceInteractor
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import org.koin.dsl.module

val domainModule = module {
    single<SettingPreferenceUseCase> { SettingPreferenceInteractor(get()) }
    single<UserPreferenceUseCase> { UserPreferenceInteractor(get()) }
    single<IttpizenUseCase> { IttpizenInteractor(get()) }
    single<IttpizenPagedUseCase> { IttpizenPagedInteractor(get()) }
}

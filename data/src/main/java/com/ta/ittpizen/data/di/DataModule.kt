package com.ta.ittpizen.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.IttpizenPagedRepositoryImpl
import com.ta.ittpizen.data.IttpizenRepositoryImpl
import com.ta.ittpizen.data.SettingPreferenceRepositoryImpl
import com.ta.ittpizen.data.UserPreference
import com.ta.ittpizen.data.UserPreferenceRepositoryImpl
import com.ta.ittpizen.data.datastore.SettingPreferenceDataStore
import com.ta.ittpizen.data.datastore.userPreferenceDataStore
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.data.remote.service.IttpizenService
import com.ta.ittpizen.domain.repository.IttpizenPagedRepository
import com.ta.ittpizen.domain.repository.IttpizenRepository
import com.ta.ittpizen.domain.repository.SettingPreferenceRepository
import com.ta.ittpizen.domain.repository.UserPreferenceRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(get<OkHttpClient>())
            .build()
    }
    single<IttpizenService> {
        get<Retrofit>().create(IttpizenService::class.java)
    }

    single<DataStore<UserPreference>> {
        get<Context>().userPreferenceDataStore
    }

    single { SettingPreferenceDataStore(get()) }
    single { RemoteDataSource(get()) }

    single<IttpizenRepository> { IttpizenRepositoryImpl(get()) }
    single<IttpizenPagedRepository> { IttpizenPagedRepositoryImpl(get()) }
    single<SettingPreferenceRepository> { SettingPreferenceRepositoryImpl(get()) }
    single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }

}

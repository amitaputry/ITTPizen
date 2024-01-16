package com.ta.ittpizen.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.IttpizenRepositoryImpl
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.data.remote.service.IttpizenService
import com.ta.ittpizen.domain.repository.IttpizenRepository
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

    single<RemoteDataSource> { RemoteDataSource(get()) }
    single<IttpizenRepository> { IttpizenRepositoryImpl(get()) }

}

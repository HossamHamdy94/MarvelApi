package com.example.marvel.di

import com.example.marvelapi.application.MarvelApiService
import com.example.marvelapi.utils.NetworkHelper.getBaseUrl
import com.example.marvelapi.utils.NetworkHelper.getOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule
{
    @Provides
    fun marvelApiService () : MarvelApiService
    {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MarvelApiService::class.java)

    }

}
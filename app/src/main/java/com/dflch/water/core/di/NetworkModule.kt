package com.dflch.water.core.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.dflch.water.caUsers.data.network.UserApiClient
import com.dflch.water.core.di.exceptionAPI.ResultCallAdapterFactory
import com.dflch.water.utils.Constants.MEDICIONES_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            //.baseUrl("http://192.168.72.188:9095/datasnap/rest/TMetodosClient/")
            .baseUrl(MEDICIONES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserClient(retrofit: Retrofit): UserApiClient {
        return retrofit.create(UserApiClient::class.java)
    }


}
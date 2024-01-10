package com.dflch.water.core.di

import com.dflch.water.caFoto.data.network.FotoApiClient
import com.dflch.water.caItems.data.network.ItemApiClient
import com.dflch.water.caTurnos.data.network.TurnoApiClient
import com.dflch.water.caUsers.data.network.UserApiClient
import com.dflch.water.core.di.interceptor.AuthInterceptor
import com.dflch.water.utils.Constants.MEDICIONES_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(MEDICIONES_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpclient(authInterceptor: AuthInterceptor): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserClient(retrofit: Retrofit): UserApiClient {
        return retrofit.create(UserApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideItemClient(retrofit: Retrofit): ItemApiClient {
        return retrofit.create(ItemApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideFotoClient(retrofit: Retrofit): FotoApiClient {
        return retrofit.create(FotoApiClient::class.java)
    }

    fun provideTurnoClient(retrofit: Retrofit): TurnoApiClient {
        return retrofit.create(TurnoApiClient::class.java)
    }

}
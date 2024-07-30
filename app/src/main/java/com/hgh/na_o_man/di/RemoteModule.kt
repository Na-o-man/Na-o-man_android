package com.hgh.na_o_man.di

import android.content.Context
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.auth.AccessTokenInterceptor
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    const val NETWORK_EXCEPTION_OFFLINE_CASE = "network status is offline"
    const val NETWORK_EXCEPTION_BODY_IS_NULL = "result body is null"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Auth

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NoAuth

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class S3


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @Auth
    fun provideAuthOKHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        dataStoreUtil: DataStoreUtil,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            //.authenticator()
            .addNetworkInterceptor(AccessTokenInterceptor(dataStoreUtil))
            .retryOnConnectionFailure(false)
            .build()

    @Provides
    @Singleton
    @Auth
    fun provideAuthRetrofit(
        @Auth okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @NoAuth
    fun provideNoAuthOKHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(false)
            .build()

    @Provides
    @Singleton
    @NoAuth
    fun provideNoAuthRetrofit(
        @NoAuth okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
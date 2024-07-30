package com.hgh.na_o_man.di

import com.hgh.na_o_man.data.source.remote.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.buildService(): T {
        return this.create(T::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthService(@RemoteModule.NoAuth retrofit: Retrofit): AuthService {
        return retrofit.buildService()
    }

}
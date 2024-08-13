package com.hgh.na_o_man.di

import com.hgh.na_o_man.data.source.remote.api.AgendasService
import com.hgh.na_o_man.data.source.remote.api.AuthService
import com.hgh.na_o_man.data.source.remote.api.MembersService
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.data.source.remote.api.NotificationsService
import com.hgh.na_o_man.data.source.remote.api.PhotosService
import com.hgh.na_o_man.data.source.remote.api.VoteService
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
    @Provides
    @Singleton
    fun ShareGroupsService(@RemoteModule.Auth retrofit: Retrofit): ShareGroupsService {
        return retrofit.buildService()
    }
    @Provides
    @Singleton
    fun provideNotificationsService(@RemoteModule.Auth retrofit: Retrofit): NotificationsService {
        return retrofit.buildService()
    }
    @Provides
    @Singleton
    fun provideMembersService(@RemoteModule.Auth retrofit: Retrofit) : MembersService {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun providePhotosService(@RemoteModule.Auth retrofit: Retrofit): PhotosService {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideAgendasService(@RemoteModule.Auth retrofit: Retrofit): AgendasService {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideVoteService(@RemoteModule.Auth retrofit: Retrofit): VoteService {
        return retrofit.buildService()
    }
}
package com.hgh.na_o_man.di

import com.hgh.na_o_man.data.repository.AuthRepositoryImpl
import com.hgh.na_o_man.data.source.remote.api.AuthService
import com.hgh.na_o_man.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun providesAuthRepository(api: AuthService): AuthRepository =
        AuthRepositoryImpl(api)

}
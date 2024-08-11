package com.hgh.na_o_man.di

import com.hgh.na_o_man.data.repository.AgendaRepositoryImpl
import com.hgh.na_o_man.data.repository.AuthRepositoryImpl
import com.hgh.na_o_man.data.repository.MemberRepositoryImpl
import com.hgh.na_o_man.data.repository.NotificationRepositoryImpl
import com.hgh.na_o_man.data.repository.PhotoRepositoryImpl
import com.hgh.na_o_man.data.repository.ShareGroupRepositoryImpl
import com.hgh.na_o_man.data.source.remote.api.AgendasService
import com.hgh.na_o_man.data.source.remote.api.AuthService
import com.hgh.na_o_man.data.source.remote.api.MembersService
import com.hgh.na_o_man.data.source.remote.api.NotificationsService
import com.hgh.na_o_man.data.source.remote.api.PhotosService
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.domain.repository.AgendaRepository
import com.hgh.na_o_man.domain.repository.AuthRepository
import com.hgh.na_o_man.domain.repository.MemberRepository
import com.hgh.na_o_man.domain.repository.NotificationsRepository
import com.hgh.na_o_man.domain.repository.PhotoRepository
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
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

    @Provides
    @ViewModelScoped
    fun providesShareGroupRepository(api: ShareGroupsService): ShareGroupRepository =
        ShareGroupRepositoryImpl(api)

    @Provides
    @ViewModelScoped
    fun providesNotificationRepository(api: NotificationsService): NotificationsRepository =
        NotificationRepositoryImpl(api)

    @Provides
    @ViewModelScoped
    fun providesMemberRepository(api: MembersService): MemberRepository =
        MemberRepositoryImpl(api)

    @Provides
    @ViewModelScoped
    fun provideAgendaRepository(api: AgendasService): AgendaRepository =
        AgendaRepositoryImpl(api)


//    @Provides
//    @ViewModelScoped
//    fun providesPhotoRepository(api: PhotosService): PhotoRepository =
//        PhotoRepositoryImpl(api)
}
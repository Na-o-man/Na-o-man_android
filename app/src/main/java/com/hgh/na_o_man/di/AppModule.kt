package com.hgh.na_o_man.di

import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.domain.repository.GroupRepository
import com.hgh.na_o_man.domain.repository.GroupRepositoryImpl
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import com.hgh.na_o_man.domain.usecase.share_group.CreateGroupUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGroupRepository(): GroupRepository {
        return GroupRepositoryImpl()
    }
}
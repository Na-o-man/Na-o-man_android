package com.hgh.na_o_man.di

import android.content.Context
import androidx.work.WorkManager
import com.hgh.na_o_man.data.repository.PhotoRepositoryImpl
import com.hgh.na_o_man.data.source.remote.api.PhotosService
import com.hgh.na_o_man.di.util.auth.Authenticator
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.di.util.s3.S3Util
import com.hgh.na_o_man.domain.repository.PhotoRepository
import com.hgh.na_o_man.domain.usecase.photo.PhotoPreSignedUrlUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoUploadUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Provides
    @Singleton
    fun provideAuthenticator(
        dataStore: DataStoreUtil,
        @ApplicationContext context: Context
    ): Authenticator {
        return Authenticator(dataStore, context)
    }

    @Provides
    @Singleton
    fun providesS3Utils(
        @ApplicationContext context: Context
    ) : S3Util {
        return S3Util(context)
    }

    @Provides
    @Singleton
    fun providesPhotoRepository(api: PhotosService): PhotoRepository =
        PhotoRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePhotoSignedUsecase(repository: PhotoRepository): PhotoPreSignedUrlUsecase {
        return PhotoPreSignedUrlUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideUploadServerUsecase(repository: PhotoRepository): PhotoUploadUsecase {
        return PhotoUploadUsecase(repository)
    }


    @Singleton
    @Provides
    fun provideWorkManager(
        @ApplicationContext context: Context,
    ): WorkManager {
        return WorkManager.getInstance(context)
    }

}
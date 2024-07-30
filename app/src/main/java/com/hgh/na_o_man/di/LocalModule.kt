package com.hgh.na_o_man.di

import android.content.Context
import com.hgh.na_o_man.di.util.auth.Authenticator
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.di.util.s3.S3Util
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
    fun providesS3Utils() : S3Util {
        return S3Util()
    }
}
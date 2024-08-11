package com.hgh.na_o_man.di

import com.hgh.na_o_man.di.util.work_manager.enqueue.DownloadEnqueuer
import com.hgh.na_o_man.di.util.work_manager.enqueue.DownloadEnqueuerImpl
import com.hgh.na_o_man.di.util.work_manager.enqueue.UploadEnqueuer
import com.hgh.na_o_man.di.util.work_manager.enqueue.UploadEnqueuerImpl
import com.hgh.na_o_man.di.util.work_manager.task.DownloadTask
import com.hgh.na_o_man.di.util.work_manager.task.DownloadTaskImpl
import com.hgh.na_o_man.di.util.work_manager.task.UploadTask
import com.hgh.na_o_man.di.util.work_manager.task.UploadTaskImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkModule {

    @Binds
    @Singleton
    abstract fun bindsUploadTask(uploadTaskImpl: UploadTaskImpl): UploadTask

    @Binds
    @Singleton
    abstract fun bindsUploadEnqueue(uploadEnqueuerImpl: UploadEnqueuerImpl): UploadEnqueuer

    @Binds
    @Singleton
    abstract fun bindsDownloadTask(downloadTaskImpl: DownloadTaskImpl): DownloadTask

    @Binds
    @Singleton
    abstract fun bindsDownloadEnqueue(downloadEnqueuerImpl: DownloadEnqueuerImpl): DownloadEnqueuer
}
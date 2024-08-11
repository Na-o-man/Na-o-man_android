package com.hgh.na_o_man.di.util.work_manager.enqueue

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hgh.na_o_man.di.util.work_manager.ImageDownloadWorker
import com.hgh.na_o_man.di.util.work_manager.ImageUploadWorker
import javax.inject.Inject

class DownloadEnqueuerImpl @Inject constructor(
    private val workManager: WorkManager,
) : DownloadEnqueuer {
    override fun enqueueDownloadWork(imageUrls: List<String>) {
        val data = Data.Builder()
            .putStringArray(ImageDownloadWorker.KEY_IMAGE_URL, imageUrls.toTypedArray())
            .build()
        val request = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
            .addTag("download")
            .setInputData(data)
            .build()
        workManager.enqueueUniqueWork(
            ImageDownloadWorker.UNIQUE_DOWNLOAD_WORK,
            ExistingWorkPolicy.KEEP,
            request,
        )
    }
}
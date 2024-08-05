package com.hgh.na_o_man.di.util.work_manager.enqueue

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hgh.na_o_man.di.util.work_manager.ImageUploadWorker
import javax.inject.Inject

class UploadEnqueuerImpl @Inject constructor(
    private val workManager: WorkManager,
) : UploadEnqueuer {
    override fun enqueueUploadWork(groupId: Long, uris: List<String>) {
        val data = Data.Builder()
            .putStringArray(ImageUploadWorker.KEY_FILE_URIS, uris.toTypedArray())
            .putLong(ImageUploadWorker.KEY_GROUP_ID, groupId)
            .build()
        val request = OneTimeWorkRequestBuilder<ImageUploadWorker>()
            .addTag("upload")
            .setInputData(data)
            .build()
        workManager.enqueueUniqueWork(
            ImageUploadWorker.UNIQUE_UPLOAD_WORK,
            ExistingWorkPolicy.KEEP,
            request,
        )
    }
}
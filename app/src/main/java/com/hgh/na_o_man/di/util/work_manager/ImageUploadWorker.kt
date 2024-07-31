package com.hgh.na_o_man.di.util.work_manager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.File

@HiltWorker
class ImageUploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val fileUris = inputData.getStringArray("file_uris") ?: return Result.failure()
        val signedUrls = inputData.getStringArray("signed_urls") ?: return Result.failure()

        if (fileUris.size != signedUrls.size) {
            return Result.failure()
        }

        return try {
            for (i in fileUris.indices) {
                val file = File(fileUris[i])
                //uploadImageWithPresignedUrl(file, signedUrls[i])
            }
            Result.success()
        } catch (e: Exception) {
            Log.e("S3Upload", "File upload failed", e)
            Result.failure()
        }
    }
}
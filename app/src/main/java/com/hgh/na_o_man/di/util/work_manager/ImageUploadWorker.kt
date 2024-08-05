package com.hgh.na_o_man.di.util.work_manager

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.work_manager.task.UploadTask
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImageUploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val uploadTask: UploadTask
) : CoroutineWorker(appContext, workerParams) {

    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result {

        val fileUris = inputData.getStringArray(KEY_FILE_URIS) ?: return Result.failure()
        val groupId = inputData.getLong(KEY_GROUP_ID, 0) ?: return  Result.failure()
        showNotification("사진 업로드 중")
        val result = uploadTask.upload(groupId, fileUris.map { Uri.parse(it) }.toTypedArray())

        return result.fold(
            onSuccess = { uploadCount ->
                showNotification("${uploadCount}장 사진 업로드 완료")
                Result.success()
            },
            onFailure = {
                showNotification("사진 업로드 실패")
                Result.failure()
            },
        )
    }

    private fun showNotification(content: String) {
        val notification = NotificationCompat.Builder(applicationContext, "upload_channel")
            .setContentTitle("사진 업로드")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_nangman_23)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val KEY_FILE_URIS = "file-uris"
        const val KEY_GROUP_ID = "group-id"
        const val UNIQUE_UPLOAD_WORK = "upload-work"
        const val NOTIFICATION_ID = 101
    }
}
package com.hgh.na_o_man.di.util.work_manager

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.work_manager.task.DownloadTask
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImageDownloadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val downloadTask: DownloadTask
) : CoroutineWorker(appContext, workerParams) {

    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result {

        val imageUrls = inputData.getStringArray(KEY_IMAGE_URL) ?: return Result.failure()
        showNotification("사진 다운로드 중")
        val result = downloadTask.download(imageUrls)

        return result.fold(
            onSuccess = { downloadCount ->
                showNotification("${downloadCount}장 사진 다운로드 완료")
                Result.success()
            },
            onFailure = {
                showNotification("사진 다운로드 실패")
                Result.failure()
            },
        )
    }

    private fun showNotification(content: String) {
        val notification = NotificationCompat.Builder(applicationContext, "image_channel")
            .setContentTitle("사진 다운로드")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_nangman_23)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        notificationManager.notify(DOWNLOAD_NOTIFICATION_ID, notification)
    }

    companion object {
        const val KEY_IMAGE_URL = "image-url"
        const val UNIQUE_DOWNLOAD_WORK = "download-work"
        const val DOWNLOAD_NOTIFICATION_ID = 102
    }
}
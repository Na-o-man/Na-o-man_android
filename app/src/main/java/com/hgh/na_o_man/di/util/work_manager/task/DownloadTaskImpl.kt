package com.hgh.na_o_man.di.util.work_manager.task

import android.util.Log
import com.hgh.na_o_man.di.util.s3.S3Util
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DownloadTaskImpl @Inject constructor(
    private val s3Util: S3Util,
) : DownloadTask {
    override suspend fun download(imageUrl: Array<String>): Result<Int> {

        val result = runCatching {
            var downloadCount = 0
            val successUrl = mutableListOf<String>()

            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val currentTime = dateFormat.format(Date())

            imageUrl.forEachIndexed { index, url ->
                s3Util.downloadImageJpeg(
                    filename = currentTime + "_$index",
                    downloadUrlOfImage = url
                )
                    .onSuccess {
                        downloadCount += 1
                    }.onFailure {
                        Log.d("에외확인", "$it")
                    }
            }
            downloadCount
        }

        return result
    }
}
package com.hgh.na_o_man.di.util.work_manager.task

import android.net.Uri

interface DownloadTask {
    suspend fun download( imageUrls: Array<String>): Result<Int>
}
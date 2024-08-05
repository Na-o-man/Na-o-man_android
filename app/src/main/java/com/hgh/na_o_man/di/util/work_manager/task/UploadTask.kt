package com.hgh.na_o_man.di.util.work_manager.task

import android.net.Uri

interface UploadTask {
    suspend fun upload(groupId : Long, uri: Array<Uri>): Result<Int>
}
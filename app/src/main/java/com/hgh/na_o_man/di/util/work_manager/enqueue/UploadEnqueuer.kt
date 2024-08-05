package com.hgh.na_o_man.di.util.work_manager.enqueue

interface UploadEnqueuer {
    fun enqueueUploadWork(groupId : Long, uris: List<String>)
}
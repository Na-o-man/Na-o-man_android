package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoDownloadUsecase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photoIds: List<Long>, groupId: Long) = flow {
        emit(repository.getPhotoDownload(photoIdList = photoIds, shareGroupId = groupId))
    }
}
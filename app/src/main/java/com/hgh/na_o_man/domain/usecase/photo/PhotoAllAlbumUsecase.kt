package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoAllAlbumUsecase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(shareGroupId: Long, profileId: Long) = flow {
        emit(repository.getPhotoAlbum(shareGroupId, profileId))
    }
}
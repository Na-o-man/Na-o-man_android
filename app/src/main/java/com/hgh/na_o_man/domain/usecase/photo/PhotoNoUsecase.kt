package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoNoUsecase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(shareGroupId: Long) = flow {
        emit(repository.getNoPhoto(shareGroupId))
    }
}
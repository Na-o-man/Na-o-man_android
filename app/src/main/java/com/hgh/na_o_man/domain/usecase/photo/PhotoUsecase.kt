package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import java.util.function.LongToIntFunction
import javax.inject.Inject

class PhotoUsecase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(shareGroupId: Long, profileId: Long, page: Int, size: Int) = flow {
        emit(repository.getPhotos(shareGroupId, profileId, page, size))
    }
}
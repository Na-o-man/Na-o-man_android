package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.data.dto.photo.request.PhotoIdListDto
import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoDeleteUsecase @Inject constructor(
    private val repository: PhotoRepository
){
    suspend operator fun invoke(photoIdListDto: PhotoIdListDto) = flow {
        emit(repository.deletePhoto(photoIdListDto))
    }
}
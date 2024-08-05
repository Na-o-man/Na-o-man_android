package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoUploadUsecase @Inject constructor(
    private val repository: PhotoRepository
){
    suspend operator fun invoke(photoUrlListDto: PhotoUrlListDto) = flow {
        emit(repository.postUpload(photoUrlListDto))
    }
}
package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.data.dto.photo.request.PhotoSampleUrlListDto
import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoSampleUploadUsecase @Inject constructor(
    private val repository: PhotoRepository
){
    suspend operator fun invoke(photoSampleUrlListDto: PhotoSampleUrlListDto) = flow {
        emit(repository.postSampleUpload(photoSampleUrlListDto))
    }
}
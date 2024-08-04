package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoPreSignedUrlUsecase @Inject constructor(
    private val repository: PhotoRepository
){
    suspend operator fun invoke(photoNameListDto: PhotoNameListDto) = flow {
        emit(repository.postPreSignedUrl(photoNameListDto))
    }
}
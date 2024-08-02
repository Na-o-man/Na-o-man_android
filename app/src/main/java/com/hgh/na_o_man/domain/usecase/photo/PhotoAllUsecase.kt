package com.hgh.na_o_man.domain.usecase.photo

import com.hgh.na_o_man.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoAllUsecase @Inject constructor(
    private val repository: PhotoRepository
){
    suspend operator fun invoke(shareGroupId : Long, page : Int, size : Int, sort : String) = flow {
        emit(repository.getPhotoAll(shareGroupId,page,size,sort))
    }
}
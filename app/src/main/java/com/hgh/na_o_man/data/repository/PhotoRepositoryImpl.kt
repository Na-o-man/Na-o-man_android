package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.notification.response.AcknowledgedCountDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoAllDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoPreSignedDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoUploadCountDto
import com.hgh.na_o_man.data.source.remote.api.PhotosService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.photo.PhotoAllModel
import com.hgh.na_o_man.domain.model.photo.PhotoPreSignedModel
import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel
import com.hgh.na_o_man.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api : PhotosService
) : PhotoRepository{
    override suspend fun postUpload(photoUrlListDto: PhotoUrlListDto): RetrofitResult<PhotoUploadCountModel> {
        return apiHandler({ api.postUploadAPI(photoUrlListDto) }) { response : ApiResult<PhotoUploadCountDto> -> response.data.toModel()}
    }

    override suspend fun postPreSignedUrl(photoNameListDto: PhotoNameListDto): RetrofitResult<PhotoPreSignedModel> {
        return apiHandler({ api.postPreSignedAPI(photoNameListDto) }) { response : ApiResult<PhotoPreSignedDto> -> response.data.toModel()}
    }

    override suspend fun getPhotoAll(shareGroupId : Long, page : Int, size : Int, sort : String): RetrofitResult<PhotoAllModel> {
        return apiHandler({ api.getPhotosAllAPI(shareGroupId, page, size, sort) }) { response : ApiResult<PhotoAllDto> -> response.data.toModel()}
    }
}
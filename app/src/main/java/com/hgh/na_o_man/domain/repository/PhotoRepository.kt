package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.photo.request.PhotoIdListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoSampleUrlListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoIdListResDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.photo.PhotoAllModel
import com.hgh.na_o_man.domain.model.photo.PhotoIdListResModel
import com.hgh.na_o_man.domain.model.photo.PhotoPreSignedModel
import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel

interface PhotoRepository {
    suspend fun postUpload(photoUrlListDto: PhotoUrlListDto): RetrofitResult<PhotoUploadCountModel>
    suspend fun postSampleUpload(photoSampleUrlListDto: PhotoSampleUrlListDto): RetrofitResult<PhotoUploadCountModel>
    suspend fun postPreSignedUrl(photoNameListDto: PhotoNameListDto): RetrofitResult<PhotoPreSignedModel>
    suspend fun getPhotoAll(shareGroupId: Long, page: Int, size: Int): RetrofitResult<PhotoAllModel>
    suspend fun getPhotoEtc(shareGroupId: Long, page: Int, size: Int): RetrofitResult<PhotoAllModel>
    suspend fun getPhotos(
        shareGroupId: Long,
        profileId: Long,
        page: Int,
        size: Int
    ): RetrofitResult<PhotoAllModel>
    suspend fun deletePhoto(photoIdListDto: PhotoIdListDto): RetrofitResult<PhotoIdListResModel>
}
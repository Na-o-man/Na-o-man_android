package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.photo.request.PhotoIdListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoSampleUrlListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoAllDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoDownloadUrlsDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoIdListResDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoPreSignedDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoUploadCountDto
import com.hgh.na_o_man.data.source.remote.api.PhotosService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.photo.PhotoAllModel
import com.hgh.na_o_man.domain.model.photo.PhotoDownloadUrlsModel
import com.hgh.na_o_man.domain.model.photo.PhotoIdListResModel
import com.hgh.na_o_man.domain.model.photo.PhotoPreSignedModel
import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel
import com.hgh.na_o_man.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PhotosService
) : PhotoRepository {
    override suspend fun postUpload(photoUrlListDto: PhotoUrlListDto): RetrofitResult<PhotoUploadCountModel> {
        return apiHandler({ api.postUploadAPI(photoUrlListDto) }) { response: ApiResult<PhotoUploadCountDto> -> response.data.toModel() }
    }

    override suspend fun postSampleUpload(photoSampleUrlListDto: PhotoSampleUrlListDto): RetrofitResult<PhotoUploadCountModel> {
        return apiHandler({ api.postSampleUploadAPI(photoSampleUrlListDto) }) { response: ApiResult<PhotoUploadCountDto> -> response.data.toModel() }
    }

    override suspend fun postPreSignedUrl(photoNameListDto: PhotoNameListDto): RetrofitResult<PhotoPreSignedModel> {
        return apiHandler({ api.postPreSignedAPI(photoNameListDto) }) { response: ApiResult<PhotoPreSignedDto> -> response.data.toModel() }
    }

    override suspend fun getPhotoDownload(
        shareGroupId: Long,
        photoIdList: List<Long>
    ): RetrofitResult<PhotoDownloadUrlsModel> {
        return apiHandler({ api.getPhotosDownloadAPI(photoIdList, shareGroupId) }) { response: ApiResult<PhotoDownloadUrlsDto> -> response.data.toModel() }
    }

    override suspend fun getPhotoAll(
        shareGroupId: Long,
        page: Int,
        size: Int
    ): RetrofitResult<PhotoAllModel> {
        return apiHandler({
            api.getPhotosAllAPI(
                shareGroupId,
                page,
                size
            )
        }) { response: ApiResult<PhotoAllDto> -> response.data.toModel() }
    }

    override suspend fun getPhotoEtc(
        shareGroupId: Long,
        page: Int,
        size: Int
    ): RetrofitResult<PhotoAllModel> {
        return apiHandler({
            api.getPhotosEtcAPI(
                shareGroupId,
                page,
                size
            )
        }) { response: ApiResult<PhotoAllDto> -> response.data.toModel() }
    }

    override suspend fun getPhotos(
        shareGroupId: Long,
        profildId: Long,
        page: Int,
        size: Int
    ): RetrofitResult<PhotoAllModel> {
        return apiHandler({
            api.getPhotosAPI(
                shareGroupId,
                profildId,
                page,
                size
            )
        }) { response: ApiResult<PhotoAllDto> -> response.data.toModel() }
    }

    override suspend fun deletePhoto(photoIdListDto: PhotoIdListDto): RetrofitResult<PhotoIdListResModel> {
        return apiHandler({ api.deletePhotosAPI(photoIdListDto) }) { response: ApiResult<PhotoIdListResDto> -> response.data.toModel() }
    }
}
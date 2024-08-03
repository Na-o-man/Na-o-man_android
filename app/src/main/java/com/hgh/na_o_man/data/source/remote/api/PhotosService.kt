package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.photo.response.PhotoAllDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoPreSignedDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoUploadCountDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotosService {
    @POST("photos/upload")
    suspend fun postUploadAPI() : Response<ApiResult<PhotoUploadCountDto>>

    @POST("photos.preSignedUrl")
    suspend fun postPreSignedAPI() : Response<ApiResult<PhotoPreSignedDto>>

    @GET("photos/all")
    suspend fun getPhotosAllAPI(
        @Query("shareGroupId") shareGroupId : Long,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ) : Response<ApiResult<PhotoAllDto>>
}
package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.photo.request.PhotoIdListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoAllDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoIdListResDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoPreSignedDto
import com.hgh.na_o_man.data.dto.photo.response.PhotoUploadCountDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotosService {
    @POST("photos/upload")
    suspend fun postUploadAPI(
        @Body request : PhotoUrlListDto
    ) : Response<ApiResult<PhotoUploadCountDto>>

    @POST("photos/preSignedUrl")
    suspend fun postPreSignedAPI(
        @Body request : PhotoNameListDto
    ) : Response<ApiResult<PhotoPreSignedDto>>

    @GET("photos/all")
    suspend fun getPhotosAllAPI(
        @Query("shareGroupId") shareGroupId : Long,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : Response<ApiResult<PhotoAllDto>>

    @GET("photos/etc")
    suspend fun getPhotosEtcAPI(
        @Query("shareGroupId") shareGroupId : Long,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : Response<ApiResult<PhotoAllDto>>

    @GET("photos")
    suspend fun getPhotosAPI(
        @Query("shareGroupId") shareGroupId : Long,
        @Query("profileId") profileId : Long,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : Response<ApiResult<PhotoAllDto>>

    @DELETE("photos")
    suspend fun deletePhotosAPI(
        @Body request : PhotoIdListDto
    ) : Response<ApiResult<PhotoIdListResDto>>
}
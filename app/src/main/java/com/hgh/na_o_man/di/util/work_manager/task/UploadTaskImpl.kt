package com.hgh.na_o_man.di.util.work_manager.task

import android.net.Uri
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.di.util.s3.S3Util
import com.hgh.na_o_man.domain.usecase.photo.PhotoPreSignedUrlUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoUploadUsecase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class UploadTaskImpl @Inject constructor(
    private val s3Util: S3Util,
    private val s3PreSignedUrlUsecase: PhotoPreSignedUrlUsecase,
    private val uploadServerUsecase: PhotoUploadUsecase,
) : UploadTask {
    override suspend fun upload(groupId: Long, uri: Array<Uri>): Result<Int> {

        val result = runCatching {
            var uploadCount = 0
            val successUrl = mutableListOf<String>()

            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val currentTime = dateFormat.format(Date())

            val files = uri.mapIndexed { index, uri ->
                s3Util.convertUriToJpegFile(uri, "${currentTime}_${index}")
            }

            s3PreSignedUrlUsecase(
                PhotoNameListDto(
                    photoNameList = files.map { it.name })
            ).collect { result ->
                result.onSuccess {
                    files.zip(it.preSignedUrlInfoList).forEach {
                        s3Util.uploadImageWithPreSignedUrl(it.first, it.second.preSignedUrl)
                            .onSuccess { _ ->
                                successUrl.add(it.second.photoUrl)
                            }
                    }
                    uploadServerUsecase(
                        PhotoUrlListDto(
                            photoUrlList = successUrl.toList(),
                            shareGroupId = groupId
                        )
                    ).collect{ result->
                        result.onSuccess {
                            uploadCount = it.uploadCount
                        }.onFail {
                            throw Exception("uploadServerUsecase 실패")
                        }.onException {
                            throw  it
                        }
                    }
                }.onException {
                     throw it
                }
            }
            uploadCount
        }

        return result
    }
}
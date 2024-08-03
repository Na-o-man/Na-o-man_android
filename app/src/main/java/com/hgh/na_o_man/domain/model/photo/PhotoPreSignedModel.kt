package com.hgh.na_o_man.domain.model.photo

import com.hgh.na_o_man.data.dto.photo.response.PreSignedUrlInfoDto

data class PhotoPreSignedModel(
    val preSignedUrlInfoList: List<PreSignedUrlInfoDto>
)

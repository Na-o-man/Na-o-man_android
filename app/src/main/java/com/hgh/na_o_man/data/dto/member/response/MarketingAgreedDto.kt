package com.hgh.na_o_man.data.dto.member.response

import com.hgh.na_o_man.domain.model.member.MarketingAgreedModel

data class MarketingAgreedDto(
    val marketingAgreed: Boolean
) {
    fun toModel() = MarketingAgreedModel(
        marketingAgreed = this.marketingAgreed
    )
}
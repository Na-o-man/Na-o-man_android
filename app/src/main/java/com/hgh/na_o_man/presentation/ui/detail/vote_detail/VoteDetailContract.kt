package com.hgh.na_o_man.presentation.ui.detail.vote_detail

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.agenda.AgendaDetailInfoModel
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListContract
import com.hgh.na_o_man.presentation.ui.main.mypage.MyPageContract

class VoteDetailContract {

    data class VoteDetailViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val isVoteMode : Boolean = false,
        val isDialogVisible: Boolean = false,
        val userInfo : AuthInfoModel = AuthInfoModel(),
        val agendaInfo : AgendaDetailInfoModel = AgendaDetailInfoModel(),
        val clickPhoto : Dummy = Dummy(),
        val photos: List<Dummy> = listOf(
            Dummy(
                id = 1,
                dummyString = "https://cdn.huffingtonpost.kr/news/photo/202108/112938_215427.jpeg"
            ),
            Dummy(
                id = 2,
                dummyString = "https://i.namu.wiki/i/NrfpBGhBC1jHPViEcFvmiY8bRRXdHIi37RvHzpvZPWoj6TFxVBYhsQfekH7FaZGvHqWNShvof6SHYDbFHh0IitHZLQ1GfS3IXuHObYffMwxt-P3v1rR6BkaojDEl63zfgNfSJFByffjppG_tCjtqBA.webp"
            ),
            Dummy(
                id = 3,
                dummyString = "https://i.namu.wiki/i/NrfpBGhBC1jHPViEcFvmiY8bRRXdHIi37RvHzpvZPWoj6TFxVBYhsQfekH7FaZGvHqWNShvof6SHYDbFHh0IitHZLQ1GfS3IXuHObYffMwxt-P3v1rR6BkaojDEl63zfgNfSJFByffjppG_tCjtqBA.webp"
            ),
            Dummy(
                id = 4,
                dummyString = "https://i.namu.wiki/i/NrfpBGhBC1jHPViEcFvmiY8bRRXdHIi37RvHzpvZPWoj6TFxVBYhsQfekH7FaZGvHqWNShvof6SHYDbFHh0IitHZLQ1GfS3IXuHObYffMwxt-P3v1rR6BkaojDEl63zfgNfSJFByffjppG_tCjtqBA.webp"   ),
            Dummy(
                id = 5,
                dummyString = "https://i.namu.wiki/i/NrfpBGhBC1jHPViEcFvmiY8bRRXdHIi37RvHzpvZPWoj6TFxVBYhsQfekH7FaZGvHqWNShvof6SHYDbFHh0IitHZLQ1GfS3IXuHObYffMwxt-P3v1rR6BkaojDEl63zfgNfSJFByffjppG_tCjtqBA.webp"
            )
        ),
    ) : ViewState

    sealed class VoteDetailSideEffect : ViewSideEffect {

        object NaviBack : VoteDetailSideEffect()
        data class ShowToast(val msg: String) : VoteDetailSideEffect()
    }

    sealed class VoteDetailEvent : ViewEvent {
        object InitVoteDetailScreen : VoteDetailEvent()
        data class OnCLickNotVoteModeImage(val photo : Dummy): VoteDetailEvent()
        data class OnClickVoteModeImage(val photo : Dummy) : VoteDetailEvent()
        object OnClickBackOnVote : VoteDetailEvent()
        object OnCLickBack : VoteDetailEvent()
        data class OnClickCancelVote(val photoId: Long): VoteDetailEvent()
        object OnClickVote : VoteDetailEvent()
        object OnClickFinish :VoteDetailEvent()
        data class OnClickInject(val text: String, val photoId : Long) : VoteDetailEvent()
        object OnDialogClosed : VoteDetailEvent()

    }
}
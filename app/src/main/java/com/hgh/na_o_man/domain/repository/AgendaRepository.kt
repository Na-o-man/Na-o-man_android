package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel

interface AgendaRepository {
    suspend fun getAgenda(shareGroupId : Long, page : Int, size : Int) : RetrofitResult<AgendaInfoListModel>
}
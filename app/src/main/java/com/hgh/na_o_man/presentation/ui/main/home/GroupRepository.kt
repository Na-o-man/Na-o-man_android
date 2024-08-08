package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.GroupDummy
import javax.inject.Inject

interface GroupRepository {
    suspend fun getGroups(): List<GroupDummy>
}

class GroupRepositoryImpl @Inject constructor(): GroupRepository {
    override suspend fun getGroups(): List<GroupDummy> {
        return listOf(
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 제목이 너무 길어서 화면 밖으로 튀어나갈 것만 같아",
                participantCount = 5,
                date = "2024-07-20"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            )
        )
    }
}
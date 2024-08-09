package com.hgh.na_o_man.presentation.ui.add.joingroup


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hgh.na_o_man.data.dto.share_group.response.ProfileInfoDto

@Composable
fun AcceptWho1(
    navController: NavController,
    profiles: List<ProfileInfoDto>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        profiles.forEach { profile ->
            UserInfo(profile)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun UserInfo(profile: ProfileInfoDto) {
    Column {
        Text(text = profile.name)
        Text(text = "Member ID: ${profile.memberId}")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAcceptWho1() {
    val navController = NavHostController(context = LocalContext.current)
    AcceptWho1(
        navController = navController,
        profiles = listOf(
            ProfileInfoDto("image1", 1, "John Doe", 1),
            ProfileInfoDto("image2", 2, "Jane Smith", 2),
            ProfileInfoDto("image3", 3, "Alice Johnson", 3)
        )
    )
}


//@Preview(showBackground = true)
//@Composable
//fun Preview10() {
//    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
//    AcceptWho1()
//}

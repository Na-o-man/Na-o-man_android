import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

//@Composable
//fun IconButtonWithBorderExample() {
//    IconButton(
//        onClick = { /* TODO: 아이콘 버튼 클릭 시 실행할 동작 */ },
//        modifier = Modifier
//            .padding(8.dp) // 테두리와 아이콘 사이의 여백
//            .border(
//                BorderStroke(2.dp, Color.White), // 흰색 테두리
//                shape = MaterialTheme.shapes.small // 테두리 모양
//            )
//    ) {
//        Icon(
//            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_cloud_138), // 아이콘 리소스 ID를 사용
//            contentDescription = "Example Icon",
//            tint = Color(0xFFBBCFE5) // 아이콘 색상 설정
//        )
//    }
//}

@Composable
fun CloudBtn(
    title : String ,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_big_cloud_138), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color(0xAA8D8D8D),
            modifier = modifier.blur(5.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_big_cloud_138), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color.Unspecified // 아이콘 색상 설정
        )

        Text(
            text = title,
            color = Color(0xFF748292),
            style = com.hgh.na_o_man.presentation.theme.Typography.labelMedium
        )
    }
}

@Composable
fun CloudWhiteBtn(
    title : String ,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cloud_138), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color(0xAA8D8D8D),
            modifier = Modifier.blur(5.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cloud_138), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color.Unspecified // 아이콘 색상 설정
        )

        Text(
            text = title,
            color = Color(0xFF4879AF),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SmallCloudBtn(
    title : String ,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_small_cloud_112), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color(0xAA8D8D8D),
            modifier = modifier.blur(5.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_small_cloud_112), // 아이콘 리소스 ID를 사용
            contentDescription = "Example Icon",
            tint = Color.Unspecified // 아이콘 색상 설정
        )

        Text(
            text = title,
            color = Color(0xFF748292),
            style = com.hgh.na_o_man.presentation.theme.Typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIconButtonWithBorderExample() {
    CloudBtn(title = "지난\n안건")
    SmallCloudBtn(title = "이미지")
}
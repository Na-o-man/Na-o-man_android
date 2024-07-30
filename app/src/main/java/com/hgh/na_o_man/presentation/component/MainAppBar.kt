package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.presentation.component.type.AppBarMenu
@Composable
fun EndAppBar(
    modifier: Modifier = Modifier,
    onEndClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = null,
        menu = AppBarMenu.MYPAGE,
        onEndClick = onEndClick,
        onStartClick = { },
        onNextClick = { } // next 버튼 클릭 이벤트 추가
    )
}

@Composable
fun StartAppBar(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier
            .padding(start = 10.dp, top = 10.dp)
            .size(76.dp),
        back = AppBarMenu.BACK,
        menu = null,
        onStartClick = onStartClick,
        onEndClick = { },
        onNextClick = { } // next 버튼 클릭 이벤트 추가
    )
}

@Composable
fun StartEndAppBar(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = AppBarMenu.BACK,
        menu = AppBarMenu.MYPAGE,
        onEndClick = onEndClick,
        onStartClick = onStartClick,
        onNextClick = { } // next 버튼 클릭 이벤트 추가
    )
}

@Composable
fun BackAndSelectAppBar(
    modifier: Modifier = Modifier,
    isMenuClick: Boolean,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = AppBarMenu.BACK,
        menu = AppBarMenu.CHECK,
        isMenuClick = isMenuClick,
        onEndClick = onEndClick,
        onStartClick = onStartClick,
        onNextClick = { } // next 버튼 클릭 이벤트 추가
    )
}

@Composable
fun NextAppBar1(
    modifier: Modifier = Modifier,
    back: AppBarMenu? = null,
    menu: AppBarMenu? = null,
    next1: AppBarMenu? = AppBarMenu.Next1,
    next2: AppBarMenu? = null,
    isMenuClick: Boolean = false,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = null,
        menu = null,
        next1 = AppBarMenu.Next1,
        next2 = null,
        isMenuClick = isMenuClick,
        onStartClick = onStartClick,
        onEndClick = onEndClick,
        onNextClick = onNextClick // next 버튼 클릭 이벤트 전달
    )
}

@Composable
fun NextAppBar2(
    modifier: Modifier = Modifier,
    back: AppBarMenu? = null,
    menu: AppBarMenu? = null,
    next1: AppBarMenu? = null,
    next2: AppBarMenu? = AppBarMenu.Next2,
    isMenuClick: Boolean = false,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = null,
        menu = null,
        next1 = null,
        next2 = AppBarMenu.Next2,
        isMenuClick = isMenuClick,
        onStartClick = onStartClick,
        onEndClick = onEndClick,
        onNextClick = onNextClick // next 버튼 클릭 이벤트 전달
    )
}



@Composable
private fun MainAppBar(
    modifier: Modifier = Modifier,
    back: AppBarMenu? = null,
    menu: AppBarMenu? = null,
    next1: AppBarMenu? = null,
    next2: AppBarMenu? = null,
    isMenuClick: Boolean = false,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
    onNextClick: () -> Unit // next 버튼 클릭 이벤트 추가
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {


        if (back != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = back.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = back.contentDescription),
                modifier = Modifier
                    .padding(start = back.horizontalPadding)
                    .clickable { onStartClick() }
                    .align(Alignment.CenterStart),
            )
        }
        if (menu != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = menu.icon),
                tint = (if (isMenuClick) {
                    Color.Yellow
                } else {
                    Color.Unspecified
                }) as Color,
                contentDescription = stringResource(id = menu.contentDescription),
                modifier = Modifier
                    .padding(end = menu.horizontalPadding)
                    .clickable { onEndClick() }
                    .align(Alignment.CenterEnd),
            )
        }

        // 다음 버튼 아이콘 추가
        if (next1 != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = next1.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = next1.contentDescription),
                modifier = Modifier
                    .clickable { onNextClick() }
                    .align(Alignment.CenterEnd)
            )
        }

        // 구름 다음 버튼 아이콘 추가
        if (next2 != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = next2.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = next2.contentDescription),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onNextClick() }
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun AppBarPreview() {
// <<<<<<< feat/photo-list-ui
//     BackAndSelectAppBar(
//         onEndClick = {},
//         onStartClick = {},
//         isMenuClick = true
// =======
//     StartAppBar(
//         onStartClick = {}
// >>>>>>> dev
//     )
// }

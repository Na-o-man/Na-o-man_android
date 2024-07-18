package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.presentation.component.type.AppBarMenu
import com.hgh.samplecompose.R

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
    )
}

@Composable
fun StartAppBar(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        back = AppBarMenu.BACK,
        menu = null,
        onStartClick = onStartClick,
        onEndClick = {}
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
        menu = AppBarMenu.BACK,
        onEndClick = onEndClick,
        onStartClick = onStartClick,
    )
}

@Composable
private fun MainAppBar(
    modifier: Modifier = Modifier,
    back: AppBarMenu? = null,
    menu: AppBarMenu? = null,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
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
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onStartClick() }
                    .align(Alignment.CenterStart),
            )
        }
        if (menu != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = menu.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = menu.contentDescription),
                modifier = Modifier
                    .padding(end = menu.horizontalPadding)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onEndClick() }
                    .align(Alignment.CenterEnd),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    StartAppBar(
        onStartClick = {}
    )
}

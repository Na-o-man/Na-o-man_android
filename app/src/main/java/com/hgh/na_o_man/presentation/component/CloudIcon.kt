package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.LightWhite


@Composable
fun StartTopCloud() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_start_cloud_430),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = Modifier
            .fillMaxWidth()
            .blur(10.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_start_cloud_430),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun StartBottomCloud() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_bottom_cloud_73),
            contentDescription = stringResource(R.string.back_description),
            tint = LightWhite,
            modifier = Modifier
                .fillMaxWidth()
                .blur(10.dp)
                .align(Alignment.BottomStart) // 맨 아래 왼쪽 정렬
        )
        Icon(
            painter = painterResource(R.drawable.ic_bottom_cloud_73),
            contentDescription = stringResource(R.string.back_description),
            tint = LightWhite,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart) // 맨 아래 왼쪽 정렬
        )
    }
}

@Composable
fun EndTopCloud() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_end_cloud_386),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = Modifier
            .fillMaxWidth()
            .blur(10.dp)
            .offset(x = 30.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_end_cloud_386),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = 30.dp)
    )
}

@Composable
fun TopCloud() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_cloud_346),
        contentDescription = stringResource(R.string.back_description),
        tint = Color(0x408D8D8D),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 33.dp)
            .offset(y = (-50).dp)
            .blur(10.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_cloud_346),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = Modifier
            .padding(horizontal = 33.dp)
            .offset(y = (-50).dp)
            .fillMaxWidth()
    )
}

@Composable
fun DecorationCloud(modifier: Modifier) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_cloud_138),
        contentDescription = stringResource(R.string.back_description),
        tint = Color(0x408D8D8D),
        modifier = modifier.blur(10.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_cloud_138),
        contentDescription = stringResource(R.string.back_description),
        tint = LightWhite,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    StartTopCloud()
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    EndTopCloud()
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    TopCloud()
}

@Preview(showBackground = true)
@Composable
fun Preview4() {
    DecorationCloud(Modifier)
}


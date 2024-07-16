package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.samplecompose.R

@Composable
fun StartTopCloud(){
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_start_cloud_430),
        contentDescription = stringResource(R.string.back_description),
        tint = Color(0x408D8D8D),
        modifier = Modifier
            .fillMaxWidth()
            .blur(10.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_start_cloud_430),
        contentDescription = stringResource(R.string.back_description),
        tint = Color.White,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun EndTopCloud(){
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_end_cloud_386),
        contentDescription = stringResource(R.string.back_description),
        tint = Color(0x408D8D8D),
        modifier = Modifier
            .fillMaxWidth()
            .blur(10.dp)
            .offset(x=30.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_end_cloud_386),
        contentDescription = stringResource(R.string.back_description),
        tint = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x=30.dp)
    )
}

@Composable
fun DecorationCloud(modifier: Modifier){
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_cloud_138),
        contentDescription = stringResource(R.string.back_description),
        tint = Color(0x408D8D8D),
        modifier = modifier.blur(10.dp)
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_cloud_138),
        contentDescription = stringResource(R.string.back_description),
        tint = Color.White,
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
    DecorationCloud(Modifier)
}

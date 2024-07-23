package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R

@Composable
fun Line(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
    )
}

@Composable
fun LineSymbol(modifier: Modifier) {
    Box {
        Line(modifier = modifier.align(Alignment.Center))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(9.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun line() {
    LineSymbol(Modifier)
}
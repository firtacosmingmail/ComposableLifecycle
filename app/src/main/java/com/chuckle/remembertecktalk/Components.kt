package com.chuckle.remembertecktalk

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun MyText(modifier: Modifier = Modifier, text: String, fontSize: TextUnit = 14.sp) {
    Log.d("Recomposition", "Composing the Text [$text]")

    Text(
        modifier = modifier,
        fontSize = fontSize,
        text = text
    )
}

@Composable
fun MyColumn(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Log.d("Recomposition", "Composing the Column")
    Column(
        modifier = modifier
    ){
        content()
    }
}

@Composable
fun MyImage(modifier: Modifier, imageId: Int) {
    Log.d("Recomposition", "Composing the Image")

    Image(
        modifier = modifier,
        painter = painterResource(id = imageId), contentDescription = "ic lancer"
    )
}

@Composable
fun MyRow(
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Log.d("Recomposition", "Composing the Row")
    Row(
        modifier = modifier
    ){
        content()
    }
}
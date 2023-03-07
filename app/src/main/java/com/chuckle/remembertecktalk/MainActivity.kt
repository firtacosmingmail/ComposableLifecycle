package com.chuckle.remembertecktalk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                Scaffold(
                    content = {
                        MainContent()
                    }
                )
            }
        }
    }

    @Composable
    @Preview
    private fun MainContent() {
        var count by remember { mutableStateOf(0) }

        var title by remember{ mutableStateOf("Check me out!") }
        var imageId by remember{ mutableStateOf(R.drawable.pisicuta) }

        MyColumn(
        ) {
            MyRow(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                MyImage(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(50.dp),
                    imageId = imageId
                )
                MyColumn(
                    modifier = Modifier
                ) {
                    MyText(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        text = title,
                    )
                    MyText(
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 12.sp,
                        text = "I have the greatest cats of all times!"
                    )
                }

            }

            Button(onClick = {
                count++
            }) {
                MyText(
                    text = "Increment counter",
                )
            }

            Button(onClick = {
                title = "Counter is now $count"
            }) {
                MyText(
                    text = "Change text",
                )
            }

            Button(onClick = {
                imageId = if ( count %2 == 0 ) {
                    R.drawable.locki
                } else {
                    R.drawable.pisicuta
                }
            }) {
                MyText(
                    text = "Change image",
                )
            }
        }
    }

    private @Composable
    fun MyText(modifier: Modifier = Modifier, text: String, fontSize: TextUnit = 14.sp) {
        Log.d("Recomposition", "Composing the Text [$text]")

        Text(
            modifier = modifier,
            fontSize = fontSize,
            text = text
        )
    }

    private @Composable
    fun MyColumn(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
        Log.d("Recomposition", "Composing the Column")
        Column(
            modifier = modifier
        ){
            content()
        }
    }

    private @Composable
    fun MyImage(modifier: Modifier, imageId: Int) {
        Log.d("Recomposition", "Composing the Image")

        Image(
            modifier = modifier,
            painter = painterResource(id = imageId), contentDescription = "ic lancer"
        )
    }

    private @Composable
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


}

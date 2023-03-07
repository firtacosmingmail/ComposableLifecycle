package com.chuckle.remembertecktalk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
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
            modifier = Modifier.scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            MainRow(title, imageId)

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

            TheList()
        }
    }

    @Composable
    fun MainRow(title: String, imageId: Int) {
        Log.d("Recomposition", "Composing the MainRow")
        MyRow(
            modifier = Modifier
                .padding(top = 5.dp)
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

    }
}

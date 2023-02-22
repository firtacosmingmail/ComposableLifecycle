package com.chuckle.remembertecktalk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                Scaffold(
                    content = {
                        MainContent(it)
                    }
                )
            }
        }
    }

    @Composable
    private fun MainContent(it: PaddingValues) {
        var text by remember{ mutableStateOf("Here's Bessy!")}

        Column{
            TextField(
                value = text,
                onValueChange = {
                    text = it
                }
            )
            StatefulText(initialState = text)

        }
    }

    @Composable
    fun StatefulText(
        initialState: String
    ){

        var text by  remember(initialState){ mutableStateOf(initialState) }

        TextField(value = text, onValueChange = {
            text = it
        })

    }
}
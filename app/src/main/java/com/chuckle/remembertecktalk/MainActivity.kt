package com.chuckle.remembertecktalk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

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
        val vm: MainViewModel = viewModel()
        val state by vm.stateLd.observeAsState()

        Column{
            TextField(
                value = state?.text ?: "",
                onValueChange = {
                    vm.onValueChanged(it)
                }
            )

            Button(onClick = {
                vm.onSaveValue()
            }) {
                Text("Save")
            }

            ListOfTexts(
                state?.texts ?: emptyList()
            )

        }
    }

    @Composable
    private fun ListOfTexts(texts: List<String>) {
        Column() {
            Text("The saved texts are:")
            for(text in texts) {
                Text(text)
            }
        }
    }

}
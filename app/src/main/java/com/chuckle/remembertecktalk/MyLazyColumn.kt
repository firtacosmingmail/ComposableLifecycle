package com.chuckle.remembertecktalk

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TheList() {
    val vm: AnimalListViewModel = viewModel()

    val elements: MyResult<List<String>> by vm.animalsLD.observeAsState(MyResult(emptyList()))

    val textValue:String  by vm.newAnimalLD.observeAsState("")

    MyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        TextField(value = textValue, onValueChange = { vm.onNewAnimalChanged(it) })
        MyButton(
            onClick = {
                vm.onNewAnimalAddedPressed()
            }
        ) {
            MyText(text = "Add new animal")
        }
        
        MyLazyColumn(
            modifier = Modifier,
            elements = elements.data
        )

    }
}

@Composable
fun MyButton(onClick: () -> Unit, content: @Composable RowScope.() -> Unit) {
    Log.d("Recomposition","Composing the Button")
    Button(
        onClick = onClick
    ) {
        content()
    }
}

@Composable
fun MyLazyColumn(modifier: Modifier = Modifier, elements: List<String>) {
    Log.d("Recomposition","Composing the LazyColumn")
    LazyColumn(
        modifier = Modifier
    ) {
        items(elements.size) { index ->
            MyLazyItem(modifier = Modifier, item = elements[index])
        }
    }
}

@Composable
fun MyLazyItem(modifier: Modifier = Modifier, item: String) {
    MyText(text = item)
}

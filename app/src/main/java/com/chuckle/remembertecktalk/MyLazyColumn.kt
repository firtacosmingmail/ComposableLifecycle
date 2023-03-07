package com.chuckle.remembertecktalk

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            elements = elements.data,
            onItemClicked = vm::onItemPressed,
            onItemDeleted = vm::onItemDeleted
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
fun MyLazyColumn(modifier: Modifier = Modifier,
                 elements: List<String>,
                 onItemClicked: (Int) -> Unit,
                 onItemDeleted: (Int) -> Unit) {
    Log.d("Recomposition","Composing the LazyColumn")
    LazyColumn(
        modifier = modifier.scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        items(elements.size) { index ->
            MyLazyItem(
                modifier = Modifier,
                item = elements[index],
                index = index,
                onItemClicked = onItemClicked,
                onItemDeleted = onItemDeleted,
                extraPadding = if(elements[index].contains("[")) index * 10 else 0
            )
        }
    }
}

@Composable
fun MyLazyItem(
    modifier: Modifier = Modifier,
    item: String,
    index: Int,
    onItemClicked: (Int) -> Unit,
    onItemDeleted: (Int) -> Unit,
    extraPadding: Int = 0
) {
    Row(
        modifier = modifier
            .height((20 + extraPadding).dp)
            .clickable {
                onItemClicked(index)
            }
    ) {
        MyText(text = item)
        MyText(
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    onItemDeleted(index)
                },
            text = "Delete"
        )
    }
}

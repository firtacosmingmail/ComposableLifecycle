package com.chuckle.remembertecktalk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MyState (
    val texts: MutableList<String> = mutableListOf(),
    var text: String = "asd"
)

class MyResult<T>(
    val data: T
){}

class MainViewModel: ViewModel() {
    val state: MyState = MyState()
    var stateLd: MutableLiveData<MyResult<MyState>> = MutableLiveData<MyResult<MyState>>().apply { value = MyResult(state) }


    fun onValueChanged(newValue: String) {
        state.text = newValue
        stateLd.postValue(MyResult(state))
    }

    fun onSaveValue() {
        state.texts.add(state.text)
        onValueChanged("")
        stateLd.postValue(MyResult(state))
    }
}

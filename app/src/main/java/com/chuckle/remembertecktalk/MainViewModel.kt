package com.chuckle.remembertecktalk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MyState (
    val texts: MutableList<String> = mutableListOf(),
    var state: String = ""
)

class MainViewModel: ViewModel() {
    val state: MyState = MyState()
    var stateLd: MutableLiveData<MyState> = MutableLiveData<MyState>().apply { value = state }


    fun onValueChanged(newValue: String) {
        state.state = newValue
        stateLd.postValue(state)
    }

    fun onSaveValue() {
        state.texts.add(state.state)
        onValueChanged("")
        stateLd.postValue(state)
    }
}

package com.chuckle.remembertecktalk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    var stateLd: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }


    fun onValueChanged(newValue: String) {
        stateLd.postValue(newValue)
    }
}

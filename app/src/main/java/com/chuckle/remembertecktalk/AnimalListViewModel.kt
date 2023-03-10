package com.chuckle.remembertecktalk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MyResult<T>(
    val data: T
){}

class AnimalListViewModel: ViewModel() {
    var counter = 1

    var animals = mutableListOf("1. Loky", "2. Paty", "3. Gingea")
    var animalsLD = MutableLiveData<MyResult<List<String>>>(MyResult(animals))

    var newAnimal = ""
    var newAnimalLD = MutableLiveData(newAnimal)

    fun onNewAnimalChanged(newAnimalName: String){
        newAnimal = newAnimalName
        newAnimalLD.postValue(newAnimalName)
    }

    fun onNewAnimalAddedPressed() {
        animals.add(newAnimal)
        newAnimal = ""
        newAnimalLD.postValue("")
        animalsLD.postValue(MyResult(animals))
    }

    fun onItemPressed(index: Int) {
        counter ++
        animals[index] += " [${counter}]"
        animalsLD.postValue(MyResult(animals))
    }

    fun onItemDeleted(index: Int) {
        if ( animals.size > index ) {
            animals.removeAt(index)
            animalsLD.postValue(MyResult(animals))
        }
    }

}

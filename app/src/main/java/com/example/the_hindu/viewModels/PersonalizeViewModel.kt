package com.example.the_hindu.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.the_hindu.model.PersonaliseModel
import com.example.the_hindu.repositories.PersonaliseRepository

class PersonalizeViewModel : ViewModel() {

    val repository = PersonaliseRepository()

    val selectionList = MutableLiveData<List<PersonaliseModel>>()

    fun getList(): LiveData<List<PersonaliseModel>> {
        return selectionList
    }

    fun getCategoryName() {
        val result = repository.getSelectionList()
        selectionList.value = result
    }

    fun getStateNAme(){
        val result = repository.getStateList()
        selectionList.value = result
    }
}
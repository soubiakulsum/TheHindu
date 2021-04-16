package com.example.thehinduclone.kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thehinduclone.kotlin.model.PremiumModel
import com.example.thehinduclone.kotlin.repositories.PremiumRepository

class PremiumViewModel : ViewModel() {

    val repository = PremiumRepository()

    val selectionList = MutableLiveData<List<PremiumModel>>()

    fun getList(): LiveData<List<PremiumModel>> {
        return selectionList
    }


}
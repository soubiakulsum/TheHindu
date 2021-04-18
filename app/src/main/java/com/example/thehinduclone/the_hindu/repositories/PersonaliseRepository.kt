package com.example.thehinduclone.the_hindu.repositories

import com.example.thehinduclone.the_hindu.models.PersonaliseModel
import com.example.thehinduclone.the_hindu.utils.Selecting

class PersonaliseRepository {

    fun getSelectionList() : List<PersonaliseModel>{

        return Selecting.getCategory()

    }

    fun getStateList() : List<PersonaliseModel>{
        return Selecting.getState()
    }
}
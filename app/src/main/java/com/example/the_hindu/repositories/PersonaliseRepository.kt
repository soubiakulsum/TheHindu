package com.example.the_hindu.repositories

import com.example.the_hindu.model.PersonaliseModel
import com.example.the_hindu.utils.Selecting

class PersonaliseRepository {

    fun getSelectionList() : List<PersonaliseModel>{

        return Selecting.getCategory()

    }

    fun getStateList() : List<PersonaliseModel>{
        return Selecting.getState()
    }
}
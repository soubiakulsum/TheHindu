package com.thehindu.the_hindu.repositories

import com.thehindu.the_hindu.model.PersonaliseModel
import com.thehindu.the_hindu.utils.Selecting

class PersonaliseRepository {

    fun getSelectionList() : List<PersonaliseModel>{

        return Selecting.getCategory()

    }

    fun getStateList() : List<PersonaliseModel>{
        return Selecting.getState()
    }
}
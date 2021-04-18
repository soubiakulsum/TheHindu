package com.example.thehinduclone.the_hindu.utils

import com.example.thehinduclone.the_hindu.models.PersonaliseModel


class Selecting {

    companion object {
        fun getCategory(): List<PersonaliseModel> {

            val selectionList = mutableListOf<PersonaliseModel>()

            selectionList.add(PersonaliseModel("National",false))
            selectionList.add(PersonaliseModel("International",false))
            selectionList.add(PersonaliseModel("Business",false))
            selectionList.add(PersonaliseModel("Economy",false))
            selectionList.add(PersonaliseModel("Industry",false))
            selectionList.add(PersonaliseModel("Columns",false))
            selectionList.add(PersonaliseModel("Sports",false))
            selectionList.add(PersonaliseModel("Cricket",false))
            selectionList.add(PersonaliseModel("Football",false))
            selectionList.add(PersonaliseModel("Entertainment", false))
            selectionList.add(PersonaliseModel("Science", false))
            selectionList.add(PersonaliseModel("Gadget",false))
            selectionList.add(PersonaliseModel("Travel", false))
            selectionList.add(PersonaliseModel("Thread",false))
            selectionList.add(PersonaliseModel("Movies",false))
            selectionList.add(PersonaliseModel("Environment",false))
            selectionList.add(PersonaliseModel("Technology",false))
            selectionList.add(PersonaliseModel("Fitness", false))
            selectionList.add(PersonaliseModel("Books",false))
            selectionList.add(PersonaliseModel("Faith",false))
            selectionList.add(PersonaliseModel("History & Culture",false))

            return selectionList
        }

        fun getState():List<PersonaliseModel>{
            val selectionList = mutableListOf<PersonaliseModel>()

            selectionList.add(PersonaliseModel("KERALA", false))
            selectionList.add(PersonaliseModel("DELHI",false))
            selectionList.add(PersonaliseModel("KOZHIKODE",false))
            selectionList.add(PersonaliseModel("MANGALURU",false))
            selectionList.add(PersonaliseModel("ANDHRA PRADESH",false))
            selectionList.add(PersonaliseModel("KARNATAKA",false))
            selectionList.add(PersonaliseModel("TAMIL NADU", false))
            selectionList.add(PersonaliseModel("TELANGANA",false))
            selectionList.add(PersonaliseModel("OTHER STATES",false))
            selectionList.add(PersonaliseModel("BENGALURU",false))
            selectionList.add(PersonaliseModel("CHENNAI",false))
            selectionList.add(PersonaliseModel("COIMBATORE",false))
            selectionList.add(PersonaliseModel("HYDERABAD",false))
            selectionList.add(PersonaliseModel("KOCHI",false))
            selectionList.add(PersonaliseModel("KOLKATA",false))
            selectionList.add(PersonaliseModel("MADURAI",false))
            selectionList.add(PersonaliseModel("MUMBAI",false))
            selectionList.add(PersonaliseModel("PUDUCHERRY",false))
            selectionList.add(PersonaliseModel("THIRUVANANTHAPURAM",false))
            selectionList.add(PersonaliseModel("TIRUCHIPALLI",false))
            selectionList.add(PersonaliseModel("VIJAYAVADA",false))

            return selectionList
        }
    }
}
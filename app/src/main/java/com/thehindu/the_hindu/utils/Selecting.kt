package com.thehindu.the_hindu.utils

import com.thehindu.the_hindu.model.PersonaliseModel

class Selecting {

    companion object {
        fun getCategory(): List<PersonaliseModel> {

            val selectionList = mutableListOf<PersonaliseModel>()

            selectionList.add(PersonaliseModel("National"))
            selectionList.add(PersonaliseModel("International"))
            selectionList.add(PersonaliseModel("Business"))
            selectionList.add(PersonaliseModel("Economy"))
            selectionList.add(PersonaliseModel("Industry"))
            selectionList.add(PersonaliseModel("Columns"))
            selectionList.add(PersonaliseModel("Sports"))
            selectionList.add(PersonaliseModel("Cricket"))
            selectionList.add(PersonaliseModel("Football"))
            selectionList.add(PersonaliseModel("Entertainment"))
            selectionList.add(PersonaliseModel("Science"))
            selectionList.add(PersonaliseModel("Gadget"))
            selectionList.add(PersonaliseModel("Travel"))
            selectionList.add(PersonaliseModel("Thread"))
            selectionList.add(PersonaliseModel("Movies"))
            selectionList.add(PersonaliseModel("Environment"))
            selectionList.add(PersonaliseModel("Technology"))
            selectionList.add(PersonaliseModel("Fitness"))
            selectionList.add(PersonaliseModel("Books"))
            selectionList.add(PersonaliseModel("Faith"))
            selectionList.add(PersonaliseModel("History & Culture"))

            return selectionList
        }

        fun getState():List<PersonaliseModel>{
            val selectionList = mutableListOf<PersonaliseModel>()

            selectionList.add(PersonaliseModel("KERALA"))
            selectionList.add(PersonaliseModel("DELHI"))
            selectionList.add(PersonaliseModel("KOZHIKODE"))
            selectionList.add(PersonaliseModel("MANGALURU"))
            selectionList.add(PersonaliseModel("ANDHRA PRADESH"))
            selectionList.add(PersonaliseModel("KARNATAKA"))
            selectionList.add(PersonaliseModel("TAMIL NADU"))
            selectionList.add(PersonaliseModel("TELANGANA"))
            selectionList.add(PersonaliseModel("OTHER STATES"))
            selectionList.add(PersonaliseModel("BENGALURU"))
            selectionList.add(PersonaliseModel("CHENNAI"))
            selectionList.add(PersonaliseModel("COIMBATORE"))
            selectionList.add(PersonaliseModel("HYDERABAD"))
            selectionList.add(PersonaliseModel("KOCHI"))
            selectionList.add(PersonaliseModel("KOLKATA"))
            selectionList.add(PersonaliseModel("MADURAI"))
            selectionList.add(PersonaliseModel("MUMBAI"))
            selectionList.add(PersonaliseModel("PUDUCHERRY"))
            selectionList.add(PersonaliseModel("THIRUVANANTHAPURAM"))
            selectionList.add(PersonaliseModel("TIRUCHIPALLI"))
            selectionList.add(PersonaliseModel("VIJAYAVADA"))

            return selectionList
        }
    }
}
package com.thehindu.themain.repository

import androidx.lifecycle.LiveData
import com.thehindu.themain.localdatabase.savedlist.SavedDAO
import com.thehindu.themain.localdatabase.savedlist.SavedEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedRepository(val savedDAO: SavedDAO) {
    fun addNewsToDatabase(entity: SavedEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            savedDAO.addNews(entity)
        }
    }

    fun getAllSavedNews(): LiveData<List<SavedEntity>> {
        return savedDAO.getAllSavedNews()
    }

    fun getSpecificNews(nid: Int, uid: Int): Int {
        return savedDAO.checkSavedList(nid, uid)
    }

    fun deleteNews(entity: SavedEntity) {
        savedDAO.deleteNews(entity)
    }
}
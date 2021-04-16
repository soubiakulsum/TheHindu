package com.thehindu.themain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thehindu.themain.localdatabase.savedlist.SavedEntity
import com.thehindu.themain.repository.SavedRepository

class SavedViewModel(val repository: SavedRepository):ViewModel() {

    fun getAllSavedNews(entity: SavedEntity){
        repository.addNewsToDatabase(entity)
    }


}

class SavedViewModelFactory(val repository: SavedRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SavedViewModel(repository) as T
    }

}
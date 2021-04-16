package com.thehindu.themain

import android.app.Application
import com.thehindu.themain.localdatabase.HinduDatabase
import com.thehindu.themain.repository.SavedRepository

open class TheHinduApplication : Application() {
    val savedDAO by lazy {
        val roomDatabase = HinduDatabase.getDatabase(this)
        roomDatabase.getSavedDao()
    }
    val savedRepository by lazy {
        SavedRepository(savedDAO)
    }
}
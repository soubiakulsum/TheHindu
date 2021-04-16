package com.thehindu.themain.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thehindu.themain.localdatabase.savedlist.SavedDAO
import com.thehindu.themain.localdatabase.savedlist.SavedEntity

@Database(entities = arrayOf(SavedEntity::class), version = 1)
abstract class HinduDatabase() : RoomDatabase() {
    abstract fun getSavedDao(): SavedDAO

    companion object{
        private var INSTANCE: HinduDatabase? = null
        fun getDatabase(context: Context): HinduDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    HinduDatabase::class.java,
                    "the_hindu"
                )
                builder.fallbackToDestructiveMigration()
                return builder.build()
            } else {
                return INSTANCE!!
            }
        }
    }

}
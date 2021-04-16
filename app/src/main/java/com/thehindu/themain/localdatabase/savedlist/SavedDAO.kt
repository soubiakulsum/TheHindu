package com.thehindu.themain.localdatabase.savedlist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SavedDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(savedEntity: SavedEntity)

    @Query("select * from saved_table")
    fun getAllSavedNews(): LiveData<List<SavedEntity>>

    @Query("select count(*) from saved_table where news_id = :nid and uid=:user_id")
    fun checkSavedList(nid: Int, user_id: Int): Int

    @Delete
    fun deleteNews(savedEntity: SavedEntity)

}
package com.thehindu.themain.localdatabase.savedlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_table")
data class SavedEntity(
    @ColumnInfo(name = "news_id") var news_id: Int?,
    @ColumnInfo(name = "uid") var uid: Int?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
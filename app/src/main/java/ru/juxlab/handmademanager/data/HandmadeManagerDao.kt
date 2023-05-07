package ru.juxlab.handmademanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import ru.juxlab.handmademanager.data.model.Folder
import ru.juxlab.handmademanager.data.model.HandmadeObject

@Dao
interface HandmadeManagerDao {

    @Insert
    suspend fun addHandmadeObject(handmadeObject: HandmadeObject)
    @Upsert
    suspend fun saveHandmadeObject(handmadeObject: HandmadeObject)
    @Delete
    fun deleteHandmadeObject(handmadeObject: HandmadeObject)

    @Query("select * from hm_objects order by position desc")
    fun getHandmadeObjects(): LiveData<List<HandmadeObject>>

    @Upsert
    fun saveFolder(folder: Folder)

    @Delete
    fun deleteFolder(folder: Folder)
}
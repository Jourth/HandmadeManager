package ru.juxlab.handmademanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import ru.juxlab.handmademanager.data.model.Folder
import ru.juxlab.handmademanager.data.model.HandmadeObject

@Dao
interface HandmadeManagerDao {
    @Upsert
    fun saveHandmadeObject(handmadeObject: HandmadeObject)

    @Delete
    fun deleteHandmadeObject(handmadeObject: HandmadeObject)

    @Upsert
    fun saveFolder(folder: Folder)

    @Delete
    fun deleteFolder(folder: Folder)
}
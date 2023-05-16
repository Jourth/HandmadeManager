package ru.juxlab.handmademanager.data

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import ru.juxlab.handmademanager.data.model.Folder
import ru.juxlab.handmademanager.data.model.HandmadeObject
import ru.juxlab.handmademanager.data.model.HandmadeObjectImage

@Dao
interface HandmadeManagerDao {

    @Insert
    fun addHandmadeObject(handmadeObject: HandmadeObject)
    @Update
    fun saveHandmadeObject(handmadeObject: HandmadeObject)

    @Query("select * from hm_objects where objectId = :id")
    fun readHandmadeObject(id: Int): LiveData<HandmadeObject>

    @Insert
    fun addHandmadeObjectImage(handmadeObjectImage: HandmadeObjectImage): Long
    @Update
    fun saveHandmadeObjectImage(handmadeObjectImage: HandmadeObjectImage)


    fun readHandmadeObjectImage(id: Int): HandmadeObjectImage{
        val imgSize = getImageSize(id).toInt()
        val output = ByteArray(imgSize.toInt())
        var pos = 1;
        while (pos < imgSize) {
            val chunk = readHandmadeObjectImageChunk(pos, listOf(999999, imgSize-(pos-1)).min(), id)
            //val chunk = readHandmadeObjectImageChunk2(id)
            System.arraycopy(chunk, 0, output, pos-1, chunk.size)
            pos += 999999
        }
        return HandmadeObjectImage(id, BitmapFactory.decodeByteArray(output, 0, output.size))
    }


    @Query("select substr(bitmap,:startPos,:endPos) from hm_images where imageId = :id")
    fun readHandmadeObjectImageChunk(startPos: Int, endPos: Int, id: Int): ByteArray
    @Query("select bitmap from hm_images where imageId = :id")
    fun readHandmadeObjectImageChunk2(id: Int): ByteArray
    @Query("select length(bitmap) from hm_images where imageId = :id")
    fun getImageSize(id: Int): Long

    @Delete
    fun deleteHandmadeObject(handmadeObject: HandmadeObject)

    @Query("select * from hm_objects order by position desc")
    fun getHandmadeObjects(): LiveData<List<HandmadeObject>>

    @Upsert
    fun saveFolder(folder: Folder)

    @Delete
    fun deleteFolder(folder: Folder)
}
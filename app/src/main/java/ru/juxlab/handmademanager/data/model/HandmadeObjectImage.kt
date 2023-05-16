package ru.juxlab.handmademanager.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_images")
data class HandmadeObjectImage(
    @PrimaryKey(autoGenerate = true)
    var imageId: Int = 0,

    var bitmap: Bitmap? = null

){
    fun copyDataToHandmadeObjectImage(handmadeObjectImage: HandmadeObjectImage) {
        handmadeObjectImage.bitmap = bitmap
    }

}
package ru.juxlab.handmademanager.ui.edit_hmobject

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.juxlab.handmademanager.data.HandmadeManagerDao
import ru.juxlab.handmademanager.data.model.HandmadeObject
import ru.juxlab.handmademanager.data.model.HandmadeObjectImage

class HandmadeObjectViewModel(private val handmadeManagerDao: HandmadeManagerDao): ViewModel() {

    var currentHandmadeObject = HandmadeObject()
    var currentHandmadeObjectImage = HandmadeObjectImage()

    fun saveHandmadeObject(handmadeObject: HandmadeObject, handmadeObjectImage: HandmadeObjectImage, newImage: Boolean = false){
        handmadeObject.copyDataToHandmadeObject(currentHandmadeObject)
        handmadeObjectImage.copyDataToHandmadeObjectImage(currentHandmadeObjectImage)

        viewModelScope.launch(Dispatchers.IO) {
           if ((currentHandmadeObjectImage.imageId == 0) && (newImage)){
                 currentHandmadeObject.imageId = handmadeManagerDao.addHandmadeObjectImage(currentHandmadeObjectImage).toInt()
            }
            else if (newImage)
                handmadeManagerDao.saveHandmadeObjectImage(currentHandmadeObjectImage)

            if (currentHandmadeObject.objectId == 0)
                handmadeManagerDao.addHandmadeObject(currentHandmadeObject)
            else
                handmadeManagerDao.saveHandmadeObject(currentHandmadeObject)
        }
    }

    fun readHandmadeObject(id: Int): LiveData<HandmadeObject> = handmadeManagerDao.readHandmadeObject(id)

    fun readHandmadeObjectImage(id: Int, imageViewObjectImage: ImageView){
        viewModelScope.launch(Dispatchers.IO) {
            currentHandmadeObjectImage = handmadeManagerDao.readHandmadeObjectImage(id)
            withContext(Dispatchers.Main) {
                imageViewObjectImage.setImageBitmap(currentHandmadeObjectImage.bitmap)
            }
        }

    }

}
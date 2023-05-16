package ru.juxlab.handmademanager.ui.home

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.juxlab.handmademanager.data.HandmadeManagerDao
import ru.juxlab.handmademanager.data.model.HandmadeObject

class HomeViewModel(private val handmadeManagerDao: HandmadeManagerDao): ViewModel() {

    val handmadeObjectsData = handmadeManagerDao.getHandmadeObjects()

    fun readHandmadeObjectImage(id: Int, imageViewObjectImage: ImageView){
        viewModelScope.launch(Dispatchers.IO) {
            val currentHandmadeObjectImage = handmadeManagerDao.readHandmadeObjectImage(id)
            withContext(Dispatchers.Main) {
                imageViewObjectImage.setImageBitmap(currentHandmadeObjectImage.bitmap)
            }
        }

    }

}
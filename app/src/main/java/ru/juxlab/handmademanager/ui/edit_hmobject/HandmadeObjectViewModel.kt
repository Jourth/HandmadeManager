package ru.juxlab.handmademanager.ui.edit_hmobject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.juxlab.handmademanager.data.HandmadeManagerDao
import ru.juxlab.handmademanager.data.model.HandmadeObject

class HandmadeObjectViewModel(private val handmadeManagerDao: HandmadeManagerDao): ViewModel() {

    fun addHandmadeObject(handmadeObject: HandmadeObject){
        viewModelScope.launch(Dispatchers.IO) {
            handmadeManagerDao.addHandmadeObject(handmadeObject)
        }
    }
}
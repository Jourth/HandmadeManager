package ru.juxlab.handmademanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.juxlab.handmademanager.data.HandmadeManagerDao
import ru.juxlab.handmademanager.data.model.HandmadeObject

class HomeViewModel(private val handmadeManagerDao: HandmadeManagerDao): ViewModel() {

    val handmadeObjectsData = handmadeManagerDao.getHandmadeObjects()

}
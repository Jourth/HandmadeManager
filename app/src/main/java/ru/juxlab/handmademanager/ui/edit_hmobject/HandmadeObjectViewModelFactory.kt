package ru.juxlab.handmademanager.ui.edit_hmobject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.juxlab.handmademanager.data.HandmadeManagerDao

class HandmadeObjectViewModelFactory(private val handmadeManagerDao: HandmadeManagerDao): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HandmadeObjectViewModel(handmadeManagerDao) as T
    }
}
package ru.juxlab.handmademanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.juxlab.handmademanager.data.HandmadeManagerDao

class HomeViewModelFactory(private val handmadeManagerDao: HandmadeManagerDao): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(handmadeManagerDao) as T
    }
}
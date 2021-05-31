package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*

//todo: add other relevant repositories
class MainViewModel : ViewModel() {


}

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
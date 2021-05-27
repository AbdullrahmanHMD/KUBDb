package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.User
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.launch

//todo: add other relevant repositories
class MainViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }
}

class MainViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
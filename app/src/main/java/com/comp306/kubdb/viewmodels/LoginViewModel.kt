package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.entities.User
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.launch

//todo: add other relevant repositories
class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers = repository.getAllUsers().asLiveData()

    val loggedIn: MutableLiveData<Boolean> = MutableLiveData(null)
    lateinit var currentUser: LiveData<User>
//    suspend fun getUser(userID: String): User {
////        return currentUser = repository.getUser(userID).asLiveData()
//    }

    fun login(lifecycleOwner: LifecycleOwner, userID: Int, password: String) {
        viewModelScope.launch {
            currentUser = repository.getUserByCredentials(userID, password).asLiveData()
            currentUser.observe(lifecycleOwner, Observer { user ->
                loggedIn.value = user != null
            })
        }
    }
}


class LoginViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
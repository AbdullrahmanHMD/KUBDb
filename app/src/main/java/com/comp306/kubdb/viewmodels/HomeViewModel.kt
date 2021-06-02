package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.repositories.BookRepository
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.launch

//todo: add other relevant repositories
class HomeViewModel :
    ViewModel() {

    private lateinit var userRepository: UserRepository
    private lateinit var bookRepository: BookRepository

    lateinit var booksOfTopAuthor: LiveData<List<Book>>
    lateinit var averageBookRatings: LiveData<List<BookAverageRating>>


    fun getAverageRatingOfBooks(isbns: List<Int>) {
        viewModelScope.launch {
            averageBookRatings = bookRepository.getAverageRating(isbns).asLiveData()
        }
    }

    fun setRepositories(userRepository: UserRepository, bookRepository: BookRepository) {
        this.userRepository = userRepository
        this.bookRepository = bookRepository
        booksOfTopAuthor = bookRepository.getBooksOFTopAuthor().asLiveData()
    }
}


class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
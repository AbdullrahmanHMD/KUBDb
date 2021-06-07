package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.data.entities.User
import com.comp306.kubdb.repositories.BookRepository
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.launch

//todo: add other relevant repositories
class HomeViewModel :
    ViewModel() {

    lateinit var currentUser: User

    private lateinit var userRepository: UserRepository
    private lateinit var bookRepository: BookRepository

    lateinit var bookRatingsOfBestAuthor: LiveData<List<BookAverageRating>>
    lateinit var bookRatingsOfRecommendation: LiveData<List<BookAverageRating>>

    val booksOfTopAuthor: LiveData<List<Book>> by lazy {
        bookRepository.getBooksOFTopAuthor().asLiveData()
    }

    val recommendedBooks: LiveData<List<Book>>? by lazy {
        bookRepository.getRecommendedBooks(currentUser.age, currentUser.state)?.asLiveData()
    }

    fun getAverageRatingOfBestAuthor(isbns: List<Int>) {
        viewModelScope.launch {
            bookRatingsOfBestAuthor = bookRepository.getAverageRatings(isbns).asLiveData()
        }
    }

    fun getAverageRatingOfRecommendations(isbns: List<Int>) {
        viewModelScope.launch {
            bookRatingsOfRecommendation = bookRepository.getAverageRatings(isbns).asLiveData()
        }
    }

    fun setRepositories(userRepository: UserRepository, bookRepository: BookRepository) {
        this.userRepository = userRepository
        this.bookRepository = bookRepository
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
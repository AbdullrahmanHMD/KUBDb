package com.comp306.kubdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.repositories.BookRepository

class BookListViewModel :
    ViewModel() {
    private lateinit var bookRepository: BookRepository

    val allBooks: LiveData<List<Book>>? by lazy {
        bookRepository.getAllBooks().asLiveData()
    }

    fun setRepositories(bookRepository: BookRepository) {
        this.bookRepository = bookRepository
    }

}

class BookListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.repositories.BookRepository
import kotlinx.coroutines.launch

class BookListViewModel :
    ViewModel() {
    private lateinit var bookRepository: BookRepository
    val allBooks: LiveData<List<Book>>? by lazy {
        bookRepository.getAllBooks().asLiveData()
    }
    var additionalBooks: LiveData<List<Book>> = MutableLiveData()

    fun setRepositories(bookRepository: BookRepository) {
        this.bookRepository = bookRepository
    }

    fun fetchMore(lastTotalItemCount: Int) {
        viewModelScope.launch {
            additionalBooks = bookRepository.getMoreBooks(lastTotalItemCount).asLiveData()
        }
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
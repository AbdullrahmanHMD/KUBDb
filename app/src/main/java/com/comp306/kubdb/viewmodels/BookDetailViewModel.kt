package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.repositories.BookRepository

//todo: add other relevant repositories
class BookDetailViewModel :
    ViewModel() {

    private val mutableCurrentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book> get() = mutableCurrentBook

    private val mutableRating = MutableLiveData<RealNumber>()
    var rating: LiveData<RealNumber> = mutableRating

    private lateinit var bookRepository: BookRepository

    fun setRepository(bookRepository: BookRepository) {
        this.bookRepository = bookRepository
    }

    fun setArgs(currentBook: Book?, rating: RealNumber?) {
        mutableCurrentBook.value = currentBook
        mutableRating.value = rating
        if (rating == null)
            this.rating = bookRepository.getAverageRating(currentBook!!.isbn).asLiveData()
    }
}


class BookDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
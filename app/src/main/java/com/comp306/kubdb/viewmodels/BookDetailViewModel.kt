package com.comp306.kubdb.viewmodels

import androidx.lifecycle.*
import com.comp306.kubdb.data.custom.AuthorName
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.repositories.AuthorRepository
import com.comp306.kubdb.repositories.BookRepository
import kotlinx.coroutines.launch

//todo: add other relevant repositories
class BookDetailViewModel :
    ViewModel() {

    private val mutableCurrentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book> get() = mutableCurrentBook

    private val mutableRating = MutableLiveData<RealNumber>()
    var rating: LiveData<RealNumber> = mutableRating

    val authorNames: LiveData<List<AuthorName>> by lazy {
        authorRepository.getBookAuthorsNames(currentBook.value!!.isbn).asLiveData()
    }

    val similarBooks: LiveData<List<Book>> by lazy {
        bookRepository.getSimilarBooks(currentBook.value!!.isbn).asLiveData()
    }

    lateinit var ratingsOfSimilarBooks: LiveData<List<BookAverageRating>>

    private lateinit var bookRepository: BookRepository
    private lateinit var authorRepository: AuthorRepository

    fun setRepositories(bookRepository: BookRepository, authorRepository: AuthorRepository) {
        this.bookRepository = bookRepository
        this.authorRepository = authorRepository
    }

    fun setArgs(currentBook: Book?, rating: RealNumber?) {
        mutableCurrentBook.value = currentBook
        mutableRating.value = rating
        if (rating == null)
            viewModelScope.launch {
                this@BookDetailViewModel.rating =
                    bookRepository.getAverageRating(currentBook!!.isbn).asLiveData()
            }

    }

    fun getAverageRatingSimilarBooks(isbns: List<Int>) {
        viewModelScope.launch {
            ratingsOfSimilarBooks = bookRepository.getAverageRatings(isbns).asLiveData()
        }
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
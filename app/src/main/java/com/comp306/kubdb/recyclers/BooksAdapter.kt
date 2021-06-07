package com.comp306.kubdb.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.R
import com.comp306.kubdb.callbacks.ItemClickEvent
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.BookItemBinding


class BooksAdapter(
    mbooks: List<Book>,
    val imageLoader: (String, ImageView) -> Unit,
    val clickEvent: ItemClickEvent
) : RecyclerView.Adapter<BookViewHolder>() {

    var books = ArrayList<Book>(mbooks)
    var unavailableBooks = ArrayList<Book>(listOf())
    var availableBooks = ArrayList<Book>(listOf())
    var allBooks = ArrayList<Book>(listOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.clickEvent = clickEvent
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position], imageLoader)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun addAll(newBooks: List<Book>) {
        books.addAll(newBooks)
        allBooks.addAll(books)
        unavailableBooks.addAll(books.filter { it.isBorrowed == true })
        availableBooks.addAll(books.filter { it.isBorrowed == false })
        notifyDataSetChanged()
    }

    fun swapBooks(category: Int) {
        books.clear()
        when (category) {
            R.id.b_one -> {
                books.addAll(allBooks)
            }
            R.id.b_two -> {
                books.addAll(availableBooks)
            }
            R.id.b_three -> {
                books.addAll(unavailableBooks)
            }
            else -> {
                books.addAll(allBooks)
            }
        }
        notifyDataSetChanged()
    }

    fun addNewBooks(newBooks: List<Book>) {
        books.addAll(newBooks)
        allBooks.addAll(newBooks)
        unavailableBooks.addAll(newBooks.filter { it.isBorrowed == true })
        availableBooks.addAll(newBooks.filter { it.isBorrowed == false })
        println("NEW BOOK COUNT: ${books.size}")
        notifyDataSetChanged()
    }
}






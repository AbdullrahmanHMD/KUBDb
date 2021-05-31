package com.comp306.kubdb.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.databinding.BookItemBinding

class Book();

class BooksAdapter(private val mbooks: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {

    var books = ArrayList<Book>(mbooks)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun addAll(newBooks: List<Book>) {


        books.addAll(books)
        notifyDataSetChanged()
    }
}






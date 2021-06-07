package com.comp306.kubdb.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.callbacks.ItemClickEvent
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.BookItemBinding


class BooksAdapter(private val mbooks: List<Book>, val loader: (String, ImageView) -> Unit, val clickEvent: ItemClickEvent) : RecyclerView.Adapter<BookViewHolder>() {

    var books = ArrayList<Book>(mbooks)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.clickEvent = clickEvent
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position], loader)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun addAll(newBooks: List<Book>) {
        books.addAll(newBooks)
        notifyDataSetChanged()
    }
}






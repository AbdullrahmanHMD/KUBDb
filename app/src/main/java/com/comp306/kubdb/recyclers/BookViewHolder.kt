package com.comp306.kubdb.recyclers

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.BookItemBinding

class BookViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book, loader: (String, ImageView) -> Unit) {
        book.getImage({ smallImageUrl -> loader(smallImageUrl, binding.bookImg) }, binding.bookImg)
        binding.position = layoutPosition
        binding.book = book
    }
}
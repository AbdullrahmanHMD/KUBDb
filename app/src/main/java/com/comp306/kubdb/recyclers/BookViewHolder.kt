package com.comp306.kubdb.recyclers

import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.databinding.BookItemBinding

class BookViewHolder(private val binding: BookItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book){
        binding.position = layoutPosition
//        binding.book = book
    }
}
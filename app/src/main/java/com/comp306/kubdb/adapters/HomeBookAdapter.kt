package com.comp306.kubdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.SqureListItemBinding
import kotlin.math.roundToInt

class HomeBookAdapter(private val books: List<Book>, private val ratings: Map<Int, Double>) :
    RecyclerView.Adapter<BookHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding =
            SqureListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val current = books[position]
        holder.bind(current, ratings[current.isbn]!!)
    }

    override fun getItemCount(): Int {
        return books.size
    }

}

class BookHolder(val binding: SqureListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book, rating: Double) {
        book.publicationYear?.let {
            binding.yearLabel.text = it.toString()
        }

        binding.rateLabel.text = ((rating * 10.0).roundToInt() / 10.0).toString()

    }

}
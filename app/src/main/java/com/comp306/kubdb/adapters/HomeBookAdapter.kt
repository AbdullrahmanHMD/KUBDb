package com.comp306.kubdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.SquareListItemBinding
import com.comp306.kubdb.precisionTo

class HomeBookAdapter(
    private val books: List<Book>,
    private val ratings: Map<Int, Float>,
    private val loader: (String, ImageView) -> Unit,
    private val clickListener: ((Book) -> Unit)
) :
    RecyclerView.Adapter<BookHolder>() {

    init {
        println("NUMBER OF BOOKS: ${books.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding =
            SquareListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val current = books[position]
        holder.bind(current, ratings[current.isbn]!!, loader)
    }

    override fun getItemCount(): Int {
        return books.size
    }

}

class BookHolder(val binding: SquareListItemBinding, val clickListener: (Book) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book, rating: Float, loader: (String, ImageView) -> Unit) {
        book.getImage(
            { smallImageUrl -> loader(smallImageUrl, binding.thumbnail) },
            binding.thumbnail
        )
        book.publicationYear?.let {
            binding.yearLabel.text = it.toString()
        }
        binding.rateLabel.text = rating.precisionTo(1).toString()
        binding.bookCard.setOnClickListener { clickListener(book) }
    }

}
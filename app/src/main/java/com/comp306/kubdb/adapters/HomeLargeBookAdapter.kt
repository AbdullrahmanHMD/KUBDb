package com.comp306.kubdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.data.entities.Book
import com.comp306.kubdb.databinding.SquareListItemLargeBinding
import com.comp306.kubdb.precisionTo

class HomeLargeBookAdapter(
    private val books: List<Book>,
    private val ratings: Map<Int, Float>,
    private val loader: (String, ImageView) -> Unit,
    private val clickListener: ((Book) -> Unit)
) :
    RecyclerView.Adapter<LargeBookHolder>() {

    init {
        println("NUMBER OF BOOKS: ${books.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeBookHolder {
        val binding =
            SquareListItemLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LargeBookHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: LargeBookHolder, position: Int) {
        val current = books[position]
        holder.bind(current, ratings[current.isbn]!!, loader)
    }

    override fun getItemCount(): Int {
        return books.size
    }

}

class LargeBookHolder(val binding: SquareListItemLargeBinding, val clickListener: (Book) -> Unit) :
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
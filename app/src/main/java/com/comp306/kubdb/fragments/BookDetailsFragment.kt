package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.comp306.kubdb.R
import com.comp306.kubdb.adapters.HomeBookAdapter
import com.comp306.kubdb.asCommaSeparatedString
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.databinding.FragmentBookDetailsBinding
import com.comp306.kubdb.precisionTo
import com.comp306.kubdb.viewmodels.BookDetailViewModel
import com.comp306.kubdb.viewmodels.BookDetailViewModelFactory

class BookDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentBookDetailsBinding
    private val arguments: BookDetailsFragmentArgs by navArgs()
    private val viewModel: BookDetailViewModel by viewModels {
        BookDetailViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setRepositories(app.bookRepository, app.authorRepository)
        viewModel.setArgs(arguments.selectedBook, arguments.rating)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailsBinding.inflate(layoutInflater, container, false)
        viewModel.currentBook.value?.let { book ->
            binding.bookDetailsTitleTv.text = book.title
            binding.bookDetailsImg.setImageResource(R.drawable.book_cover)
            binding.publicationValueTv.text = book.publicationYear?.toString() ?: "Unknown"
            binding.publisherValueTv.text = book.publisher ?: "Unknown"
            binding.availabilityValueTv.let {
                if (book.isBorrowed == true) {
                    it.text = getString(R.string.unavailable)
                    it.setTextColor(ActivityCompat.getColor(requireContext(), R.color.pink_700))
                } else {
                    it.text = getString(R.string.available)
                    it.setTextColor(ActivityCompat.getColor(requireContext(), R.color.teal_700))
                }
            }
            loadImage(book.largeImageUrl)
            viewModel.authorNames.observe(viewLifecycleOwner, { authors ->
                binding.authors = authors.map { it.fullName }.asCommaSeparatedString()
            })
        }

        if (viewModel.rating.value != null) {
            viewModel.rating.value!!.let {
                binding.rating = it.value.precisionTo(1)
                println("rating: ${it.value.precisionTo(1)}")
            }
        } else {
            viewModel.rating.observe(viewLifecycleOwner, { rating ->
                binding.bookDetailsRating.rating = rating.value.precisionTo(1)
            })
        }

        showSimilarBooks()

        return binding.root
    }

    private fun loadImage(url: String?) {
        if (url == null)
            return //todo: set placeholder image here instead
        println("URL: $url")
        Glide.with(this).load(url).into(binding.bookDetailsImg)
    }

    private fun showSimilarBooks() {
        binding.similarBooksTitle.animate().alpha(1f).duration = 650
        viewModel.similarBooks.observe(viewLifecycleOwner, { books ->
            viewModel.getAverageRatingSimilarBooks(books.map { it.isbn })
            viewModel.ratingsOfSimilarBooks.observe(viewLifecycleOwner, { bookRatings ->
                val ratingsMap = bookRatings.map { it.getAsMapEntry() }.toMap()
                val adapter = HomeBookAdapter(books, ratingsMap, ::loader) { book ->
                    navigate(
                        BookDetailsFragmentDirections.bookDetailsFragmentToAnotherBook(
                            book,
                            RealNumber(ratingsMap[book.isbn]!!)
                        )
                    )
                }
                setAdapter(binding.similarBooksRecycler, adapter)

                binding.similarBooksRecycler.animate().alpha(1f).duration = 650
            })
        })
    }

    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        getMainActivity()?.onBackPressed()
    }

}
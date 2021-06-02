package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.comp306.kubdb.R
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
        viewModel.setRepository(app.bookRepository)
        viewModel.setArgs(arguments.selectedBook, RealNumber(arguments.rating))
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
                    it.text = "Unavailable"
                    it.setTextColor(ActivityCompat.getColor(requireContext(), R.color.pink_700))
                } else {
                    it.text = "Available"
                    it.setTextColor(ActivityCompat.getColor(requireContext(), R.color.teal_700))
                }
            }
        }

        if (viewModel.rating.value != null) {
            viewModel.rating.value!!.let {
                binding.rating = it.value.precisionTo(1)
                println("rating: ${it.value.precisionTo(1)}")
            }
        } else {
            viewModel.rating.observe(viewLifecycleOwner, {
                binding.rating = it.value.precisionTo(1)
            })
        }

        return binding.root
    }


    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        getMainActivity().onBackPressed()
    }

}
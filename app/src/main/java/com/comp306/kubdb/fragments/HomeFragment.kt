package com.comp306.kubdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.adapters.HomeBookAdapter
import com.comp306.kubdb.databinding.FragmentHomeBinding
import com.comp306.kubdb.viewmodels.HomeViewModel
import com.comp306.kubdb.viewmodels.HomeViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding

    private lateinit var app: LibraryApplication

    val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.fragment = this

        viewModel.booksOfTopAuthor.observe(viewLifecycleOwner, { books ->
            viewModel.getAverageRatingOfBooks(books.map { it.isbn })
            viewModel.averageBookRatings.observe(viewLifecycleOwner, { bookRatings ->
                val ratingsMap = bookRatings.map { it.getAsMapEntry() }.toMap()
                val adapter = HomeBookAdapter(books, ratingsMap)
                binding.category1Recycler.adapter = adapter
                binding.category1Recycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            })
        })

        return binding.root
    }


    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        navigateBack()
    }

    fun onProfileClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        //todo: navigate to profile fragment
    }

    fun onAllBooksClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        //todo: navigate to book list fragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            app = context.application as LibraryApplication
            viewModel.setRepositories(app.userRepository, app.bookRepository)
        }
    }

}


/*

var lastTotalBooksCount: Int? = 0
        val layoutManager = GridLayoutManager(activity as MainActivity, 1)
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (lastTotalBooksCount != null && lastTotalBooksCount == totalItemsCount) {
                    return
                }
                lastTotalBooksCount = totalItemsCount
                val addBooks: List<Book>? =
                    viewModel.getBookList(activity as MainActivity, lastTotalBooksCount!!)
                if (addBooks.isNullOrEmpty().not()) {
                    booksAdapter?.addAll(addBooks)
                }

            }

        }
        binding?.clRecycler?.addOnScrollListener(scrollListener!!)
 */
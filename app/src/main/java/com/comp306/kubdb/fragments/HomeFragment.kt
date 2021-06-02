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


class HomeFragment : BaseFragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setRepositories(app.userRepository, app.bookRepository)
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
                val adapter = HomeBookAdapter(books, ratingsMap) { book ->
                    navigate(
                        HomeFragmentDirections.homeToBookDetails(
                            book,
                            ratingsMap[book.isbn]!!
                        )
                    )
                }
                binding.category1Recycler.adapter.let {
                    if (it == null) {
                        binding.category1Recycler.adapter = adapter
                        binding.category1Recycler.layoutManager =
                            LinearLayoutManager(
                                context,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                    }
                }

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
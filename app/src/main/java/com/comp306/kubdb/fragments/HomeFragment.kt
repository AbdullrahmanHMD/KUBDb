package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comp306.kubdb.adapters.HomeBookAdapter
import com.comp306.kubdb.adapters.HomeLargeBookAdapter
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
        getMainActivity()?.currentUser?.let {
            viewModel.currentUser = it
            println("USER SET")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.fragment = this
        showHeaders()
        showBooksOfBestAuthor()
        showRecommendedBooks()
        return binding.root
    }

    private fun showBooksOfBestAuthor() {
        viewModel.booksOfTopAuthor.observe(viewLifecycleOwner, { books ->
            binding.pb2.visibility = View.GONE
            viewModel.getAverageRatingOfBestAuthor(books.map { it.isbn })
            viewModel.bookRatingsOfBestAuthor.observe(viewLifecycleOwner, { bookRatings ->
                val ratingsMap = bookRatings.map { it.getAsMapEntry() }.toMap()
                val adapter = HomeBookAdapter(books, ratingsMap) { book ->
                    navigate(
                        HomeFragmentDirections.homeToBookDetails(
                            book,
                            ratingsMap[book.isbn]!!
                        )
                    )
                }
                setAdapter(binding.category2Recycler, adapter)

                binding.category2Recycler.animate().alpha(1f).duration = 650
            })
        })
    }

    private fun showRecommendedBooks() {
        viewModel.recommendedBooks?.observe(viewLifecycleOwner, { books ->
            binding.pb1.visibility = View.GONE
            viewModel.getAverageRatingOfRecommendations(books.map { it.isbn })
            viewModel.bookRatingsOfRecommendation.observe(viewLifecycleOwner, { bookRatings ->
                val ratingsMap = bookRatings.map { it.getAsMapEntry() }.toMap()
                val adapter = HomeLargeBookAdapter(books, ratingsMap) { book ->
                    navigate(
                        HomeFragmentDirections.homeToBookDetails(
                            book,
                            ratingsMap[book.isbn]!!
                        )
                    )
                }
                setAdapter(binding.category1Recycler, adapter)
            })
            binding.category1Recycler.animate().alpha(1f).duration = 650
        })
    }

    private fun showHeaders() {
        binding.category1Title.animate().alpha(1f).setDuration(650).withEndAction {
            binding.category2Title.animate().alpha(1f).setDuration(650).withEndAction {
                // animate the next here
            }
        }
    }

    private fun setAdapter(
        recyclerView: RecyclerView,
        adapter: HomeBookAdapter
    ) {
        recyclerView.adapter.let {
            if (it == null) {
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
            }
        }
    }

    private fun setAdapter(
        recyclerView: RecyclerView,
        adapter: HomeLargeBookAdapter
    ) {
        recyclerView.adapter.let {
            if (it == null) {
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
            }
        }
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
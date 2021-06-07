package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.callbacks.ItemClickEvent
import com.comp306.kubdb.databinding.FragmentBookListBinding
import com.comp306.kubdb.recyclers.BooksAdapter
import com.comp306.kubdb.viewmodels.BookListViewModel
import com.comp306.kubdb.viewmodels.BookListViewModelFactory


class BookListFragment : BaseFragment() {

    lateinit var binding: FragmentBookListBinding
    var adapter: BooksAdapter? = null
    val viewModel: BookListViewModel by viewModels {
        BookListViewModelFactory()
    }

    private fun imageLoader(url: String, imageview: ImageView) {
        Glide.with(this).load(url).into(imageview)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(layoutInflater, container, false)
        viewModel.setRepositories(app.bookRepository)
        val dividerItemDecoration = DividerItemDecoration(
            binding.booksRecycler.context,
            (activity as MainActivity).resources.configuration.orientation
        )

        adapter = BooksAdapter(listOf(), ::imageLoader, object : ItemClickEvent {
            override fun onItemClicked(position: Int) {
                val dataSet = adapter?.books
                val book = dataSet!![position]
                navigate(
                    BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(
                        book,
                        null
                    )
                )
            }
        })

        val layoutManager =
            LinearLayoutManager(getMainActivity(), LinearLayoutManager.VERTICAL, false)
        binding.booksRecycler.addItemDecoration(dividerItemDecoration)
        binding.booksRecycler.layoutManager = layoutManager
        binding.booksRecycler.adapter = adapter


        viewModel.allBooks?.observe(viewLifecycleOwner, { books ->

            val filtered = books.filter { it.title.matches(Regex("[A-Z0-9a-z\\s:'.;\\-_]*")) }

            adapter?.addAll(filtered)
        })

        binding.radialGroup.setOnCheckedChangeListener { group, checkedId ->
            adapter?.swapBooks(group.checkedRadioButtonId)
        }


        binding.booksRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1).not()) {
                    println("CURRENT BOOK COUNT: ${adapter!!.itemCount}")
                    println("LOADING BOOKS...")
                    loadBooks(adapter!!.itemCount)
                }
            }
        })

        viewModel.additionalBooks.observe(viewLifecycleOwner, { new ->
            new?.let {
                adapter?.addNewBooks(new)
            }
        })
        return binding.root
    }

    private fun loadBooks(currentAmount: Int) {
        viewModel.fetchMore(currentAmount)
        viewModel.additionalBooks.observe(viewLifecycleOwner, { new ->
            new?.let {
                println("NUMBER OF NEW BOOKS: ${new.size}")
                adapter?.addNewBooks(new)
            }
        })
    }

    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        (activity as MainActivity).onBackPressed()
    }


}
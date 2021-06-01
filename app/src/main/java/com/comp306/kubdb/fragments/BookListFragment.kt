package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.callbacks.ItemClickEvent
import com.comp306.kubdb.databinding.FragmentBookListBinding
import com.comp306.kubdb.recyclers.BooksAdapter


class BookListFragment : BaseFragment() {

    lateinit var binding: FragmentBookListBinding
    var adapter: BooksAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(layoutInflater, container, false)
        val dividerItemDecoration = DividerItemDecoration(
            binding.booksRecycler.context,
            (activity as MainActivity).resources.configuration.orientation
        )

        adapter = BooksAdapter(listOf(), object : ItemClickEvent {
            override fun onItemClicked(position: Int) {
                val dataSet = adapter?.books
                val book = dataSet!![position]
                //todo: navigate to book details
            }
        })

        binding.booksRecycler.addItemDecoration(dividerItemDecoration)
        return binding.root
    }


    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        (activity as MainActivity).onBackPressed()
    }

}
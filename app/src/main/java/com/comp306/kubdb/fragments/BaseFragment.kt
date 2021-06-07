package com.comp306.kubdb.fragments

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.adapters.HomeBookAdapter

abstract class BaseFragment : Fragment() {

    protected lateinit var app: LibraryApplication

    public val navController by lazy {
        findNavController()
    }

    protected fun navigate(actionId: Int) {
        this.navController.navigate(actionId)
    }

    protected fun navigate(direction: NavDirections) {
        this.navController.navigate(direction)
    }

    protected fun navigate(actionId: Int, bundle: Bundle) {
        this.navController.navigate(actionId, bundle)
    }

    protected fun navigateBack() {
        this.navController.navigateUp()
    }

    fun getMainActivity(): MainActivity? {
        return activity as MainActivity?
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            app = context.application as LibraryApplication
        }
    }

    protected fun loader(url: String, imageView: ImageView) {
        Glide.with(this).load(url).into(imageView)
    }

    protected fun setAdapter(
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

}
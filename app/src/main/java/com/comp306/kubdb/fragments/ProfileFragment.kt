package com.comp306.kubdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comp306.kubdb.R
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        (activity as MainActivity).onBackPressed()
    }

    fun onMyBooksClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        //navigate to my books
    }
}
package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comp306.kubdb.activities.MainActivity
import com.comp306.kubdb.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        val user = getMainActivity()?.currentUser

        user?.let { itUser ->
            binding.username = "${itUser.name} ${itUser.lastName}"
            binding.ageValueTv.text = itUser.age.toString()
            binding.cityValueTv.text = itUser.city
            binding.stateValueTv.text = itUser.state
            binding.countryValueTv.text = itUser.country
        }

        return binding.root
    }


    fun onBackClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        (activity as MainActivity).onBackPressed()
    }

    fun onMyBooksClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        //navigate to my books
    }
}
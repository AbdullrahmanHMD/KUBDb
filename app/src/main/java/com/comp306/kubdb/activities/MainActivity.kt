package com.comp306.kubdb.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.comp306.kubdb.R
import com.comp306.kubdb.data.entities.User
import com.comp306.kubdb.databinding.ActivityMainBinding
import com.comp306.kubdb.fragments.HomeFragmentDirections
import com.comp306.kubdb.viewmodels.MainViewModel
import com.comp306.kubdb.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }


    lateinit var currentUser: User

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = findNavController(this, R.id.bottom_navigation_host)
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE
    }

}
package com.comp306.kubdb.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.comp306.kubdb.R
import com.comp306.kubdb.data.entities.User
import com.comp306.kubdb.databinding.ActivityMainBinding
import com.comp306.kubdb.viewmodels.MainViewModel
import com.comp306.kubdb.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }


    var currentUser: User? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("getting user extra")
        intent.extras?.let {
            currentUser = it.getSerializable("CURRENT_USER") as User?
            println("got user extra, null? ${currentUser == null}")
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = findNavController(R.id.navigation_host)
        binding.bottomNav.setupWithNavController(navController)
    }

}
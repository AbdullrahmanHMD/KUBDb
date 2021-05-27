package com.comp306.kubdb.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.R
import com.comp306.kubdb.data.User
import com.comp306.kubdb.viewmodels.MainViewModel
import com.comp306.kubdb.viewmodels.MainViewModelFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as LibraryApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        viewModel.allUsers.observe(this, { newList ->
//            newList.forEach { println(it)}
//        })
    }
}
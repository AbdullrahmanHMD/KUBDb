package com.comp306.kubdb.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.R
import com.comp306.kubdb.databinding.ActivityLoginBinding
import com.comp306.kubdb.hideKeyboard
import com.comp306.kubdb.viewmodels.LoginViewModel
import com.comp306.kubdb.viewmodels.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as LibraryApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginButton.setOnClickListener { login() }
    }

    private fun login() {
        binding.loginButton.isEnabled = false
        hideKeyboard()
        val id = binding.usernameEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        if (id.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in login information", Toast.LENGTH_LONG)
                .show()
            binding.loginButton.isEnabled = true

            return
        }

        viewModel.login(this, id.toInt(), password)
        viewModel.loggedIn.observe(
            this,
            {
                it?.let { successfulLogin ->
                    if (successfulLogin) {

                        viewModel.currentUser.value?.let { user ->
                            Toast.makeText(this, "Welcome, ${user.name}!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        val toMainIntent = Intent(this, MainActivity::class.java)
                        toMainIntent.putExtra("CURRENT_USER", viewModel.currentUser.value!!)
                        startActivity(toMainIntent)
                        finish()
                    } else {
                        Toast.makeText(this, "Incorrect ID/Password", Toast.LENGTH_SHORT).show()
                        binding.loginButton.isEnabled = true
                    }

                    viewModel.loggedIn.value = null
                }
            }
        )
    }
}
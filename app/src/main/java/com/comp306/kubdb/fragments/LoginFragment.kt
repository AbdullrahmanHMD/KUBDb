package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.R
import com.comp306.kubdb.databinding.FragmentLoginBinding
import com.comp306.kubdb.viewmodels.LoginViewModel
import com.comp306.kubdb.viewmodels.LoginViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginBinding
    private lateinit var root: View
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((activity?.application as LibraryApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        root = binding.root
        binding.loginButton.setOnClickListener {
            login()
        }
        return root
    }

    private fun login() {

        val id = binding.usernameEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        if (id.isEmpty() || password.isEmpty()) {
            Toast.makeText(this.context, "Please fill in login information", Toast.LENGTH_LONG)
                .show()
            return
        }

        viewModel.login(viewLifecycleOwner, id, password)
        viewModel.loggedIn.observe(
            viewLifecycleOwner,
            {
                it?.let { successfulLogin ->
                    if (successfulLogin) {
                        Navigation.findNavController(root)
                            .navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.currentUser.value?.let { user ->
                            Toast.makeText(context, "Welcome, ${user.name}!", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.loggedIn.value = null
                        }
                    } else
                        Toast.makeText(context, "Incorrect ID/Password", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

}
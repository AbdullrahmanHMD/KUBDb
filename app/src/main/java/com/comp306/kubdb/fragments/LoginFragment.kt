package com.comp306.kubdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.databinding.FragmentLoginBinding
import com.comp306.kubdb.hideKeyboard
import com.comp306.kubdb.viewmodels.LoginViewModel
import com.comp306.kubdb.viewmodels.LoginViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LoginFragment : BaseFragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var root: View
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((activity?.application as LibraryApplication).userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        root = binding.root
        binding.loginButton.setOnClickListener {
//            login()
        }


//        viewModel.allUsers.observe(viewLifecycleOwner, {
//            val size = it.size
//            println(size)
//            if (size > 0)
//                println(it[0])
//        })

        return root
    }

//    private fun login() {
////        navigate(R.id.action_loginFragment_to_homeFragment)
//        binding.loginButton.isEnabled = false
//        getMainActivity().hideKeyboard()
//        val id = binding.usernameEdittext.text.toString()
//        val password = binding.passwordEdittext.text.toString()
//
//        if (id.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this.context, "Please fill in login information", Toast.LENGTH_LONG)
//                .show()
//            binding.loginButton.isEnabled = true
//
//            return
//        }
//
//        viewModel.login(viewLifecycleOwner, id.toInt(), password)
//        viewModel.loggedIn.observe(
//            viewLifecycleOwner,
//            {
//                it?.let { successfulLogin ->
//                    if (successfulLogin) {
//                        navigate(
//                            LoginFragmentDirections.loginToHome(viewModel.currentUser.value!!)
//                        )
//                        viewModel.currentUser.value?.let { user ->
//                            Toast.makeText(context, "Welcome, ${user.name}!", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    } else {
//                        Toast.makeText(context, "Incorrect ID/Password", Toast.LENGTH_SHORT).show()
//                        binding.loginButton.isEnabled = true
//                    }
//
//                    viewModel.loggedIn.value = null
//                }
//            }
//        )
//    }

}
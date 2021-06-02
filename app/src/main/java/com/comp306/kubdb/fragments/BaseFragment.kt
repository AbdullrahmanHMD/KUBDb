package com.comp306.kubdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.comp306.kubdb.activities.MainActivity

abstract class BaseFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }

    protected fun navigate(actionId: Int) {
        this.navController.navigate(actionId)
    }

    protected fun navigate(actionId: Int, bundle: Bundle) {
        this.navController.navigate(actionId, bundle)
    }

    protected fun navigateBack() {
        this.navController.navigateUp()
    }

    fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

}
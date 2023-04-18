package com.example.detiapplication.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val parentRegistrationRepository = ParentRegistrationRepository(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(parentRegistrationRepository = parentRegistrationRepository) as T
    }
}
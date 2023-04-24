package com.example.detiapplication.presentation.di

import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository
import com.example.detiapplication.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel>() {
        MainViewModel(parentRegistrationRepository = get())
    }

    single<ParentRegistrationRepository> {
        ParentRegistrationRepository(context = get())
    }
}
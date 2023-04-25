package com.example.detiapplication.presentation.di

import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegistrationRepository
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import com.example.detiapplication.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel>() {
        MainViewModel(
            parentRegistrationRepository = get(),
            childrenRegistrationRepository = get()
        )
    }

    single<ParentRegistrationRepository> {
        ParentRegistrationRepository(context = get())
    }

    single<ChildrenRegistrationRepository> {
        ChildrenRegistrationRepository(context = get())
    }
}

val homeModule = module {
    viewModel<HomeViewModel>() {
        HomeViewModel(
            parentRegistrationRepository = get(),
            childrenRegistrationRepository = get()
        )
    }
}
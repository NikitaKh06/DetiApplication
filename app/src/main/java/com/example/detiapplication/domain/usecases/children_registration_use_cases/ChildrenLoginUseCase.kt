package com.example.detiapplication.domain.usecases.children_registration_use_cases

import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenLoginResponseModel
import com.example.detiapplication.domain.repository.children_repositories.ChildrenRegistrationRepository

class ChildrenLoginUseCase(private val repository: ChildrenRegistrationRepository) {

    fun execute(model: ChildrenLoginRequestModel) : ChildrenLoginResponseModel {
        return repository.loginChildren(model)
    }

}
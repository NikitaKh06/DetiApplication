package com.example.detiapplication.domain.usecases.children_registration_use_cases

import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationResponseModel
import com.example.detiapplication.domain.repository.children_repositories.ChildrenRegistrationRepository

class ChildrenRegistrationUseCase(private val repository: ChildrenRegistrationRepository) {

    fun execute(model: ChildrenRegistrationRequestModel) : ChildrenRegistrationResponseModel {
        return repository.registerChildren(model)
    }

}
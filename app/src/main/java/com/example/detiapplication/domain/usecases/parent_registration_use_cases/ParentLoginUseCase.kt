package com.example.detiapplication.domain.usecases.parent_registration_use_cases

import com.example.detiapplication.domain.models.parent_models.ParentLoginRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentLoginResponseModel
import com.example.detiapplication.domain.repository.parent_repositories.ParentRegistrationRepository

class ParentLoginUseCase(private val repository: ParentRegistrationRepository) {

    fun execute(model: ParentLoginRequestModel) : ParentLoginResponseModel {
        return repository.loginParent(model)
    }

}
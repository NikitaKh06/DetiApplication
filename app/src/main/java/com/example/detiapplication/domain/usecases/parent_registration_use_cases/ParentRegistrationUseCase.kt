package com.example.detiapplication.domain.usecases.parent_registration_use_cases

import com.example.detiapplication.domain.models.parent_models.ParentRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationResponseModel
import com.example.detiapplication.domain.repository.parent_repositories.ParentRegistrationRepository

class ParentRegistrationUseCase(private val repository: ParentRegistrationRepository) {

    fun execute(model: ParentRegistrationRequestModel) : ParentRegistrationResponseModel {
        return repository.registerParent(model)
    }

}
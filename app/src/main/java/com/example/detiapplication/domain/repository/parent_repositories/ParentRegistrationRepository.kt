package com.example.detiapplication.domain.repository.parent_repositories

import com.example.detiapplication.domain.models.parent_models.ParentLoginRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentLoginResponseModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationResponseModel
import com.example.detiapplication.domain.usecases.parent_registration_use_cases.ParentLoginUseCase

interface ParentRegistrationRepository {

    fun loginParent(model: ParentLoginRequestModel) : ParentLoginResponseModel

    fun registerParent(model: ParentRegistrationRequestModel) : ParentRegistrationResponseModel

}
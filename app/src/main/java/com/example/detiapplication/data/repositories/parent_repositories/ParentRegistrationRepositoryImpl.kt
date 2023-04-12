package com.example.detiapplication.data.repositories.parent_repositories

import com.example.detiapplication.domain.models.parent_models.ParentLoginRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentLoginResponseModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationResponseModel

class ParentRegistrationRepository {

    fun loginParent(model: ParentLoginRequestModel) : ParentLoginResponseModel {
        return ParentLoginResponseModel("")
    }

    fun registerParent(model: ParentRegistrationRequestModel) : ParentRegistrationResponseModel {
        return ParentRegistrationResponseModel("")
    }

}
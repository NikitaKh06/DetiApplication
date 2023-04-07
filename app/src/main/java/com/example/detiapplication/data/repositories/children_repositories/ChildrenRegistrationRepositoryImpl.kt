package com.example.detiapplication.data.repositories.children_repositories

import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenLoginResponseModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationResponseModel
import com.example.detiapplication.domain.repository.children_repositories.ChildrenRegistrationRepository

class ChildrenRegistrationRepositoryImpl : ChildrenRegistrationRepository{

    override fun loginChildren(model: ChildrenLoginRequestModel) : ChildrenLoginResponseModel{
        return ChildrenLoginResponseModel("")
    }

    override fun registerChildren(model: ChildrenRegistrationRequestModel) : ChildrenRegistrationResponseModel {
        return ChildrenRegistrationResponseModel("")
    }

}
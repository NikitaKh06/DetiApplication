package com.example.detiapplication.domain.repository.children_repositories

import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenLoginResponseModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationResponseModel

interface ChildrenRegistrationRepository {

    fun loginChildren(model: ChildrenLoginRequestModel) : ChildrenLoginResponseModel

    fun registerChildren(model: ChildrenRegistrationRequestModel) : ChildrenRegistrationResponseModel

}
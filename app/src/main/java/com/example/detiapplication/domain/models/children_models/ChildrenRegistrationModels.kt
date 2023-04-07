package com.example.detiapplication.domain.models.children_models

data class ChildrenLoginRequestModel(
    val parent_email: String,
    val password: String
)

data class ChildrenLoginResponseModel(
    val token: String
)

data class ChildrenRegistrationRequestModel(
    val parent_email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val age: String
)

data class ChildrenRegistrationResponseModel(
    val token: String
)

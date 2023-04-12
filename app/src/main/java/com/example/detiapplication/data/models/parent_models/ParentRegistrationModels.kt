package com.example.detiapplication.domain.models.parent_models

data class ParentLoginRequestModel(
    val email: String,
    val password: String
)

data class ParentLoginResponseModel(
    val token: String
)

data class ParentRegistrationRequestModel(
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String
)

data class ParentRegistrationResponseModel(
    val token: String
)

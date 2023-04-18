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

data class SearchChidlrenRequestModel(
    val token: String,
    val parent_email: String
)

data class SearchChidldrenResponseModel(
    var first_name: String,
    val last_name: String,
    val age: String
)

data class AddChildrenModel(
    val token: String
)
package com.example.detiapplication.data.repositories.parent_repositories

data class ReadParentProfileRequestModel(
    val token: String
)

data class ReadParentProfileResponseModel(
    val first_name: String,
    val last_name: String,
    val photo: String
)

data class ReadChildrenProfileFromParentRequestModel(
    val token: String
)

data class ReadChildrenProfileFromParentResponseModel(
    val first_name: String,
    val last_name: String,
    val age: String,
    val photo: String
)
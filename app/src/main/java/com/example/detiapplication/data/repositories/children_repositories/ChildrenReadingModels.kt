package com.example.detiapplication.data.models.children_models

data class ReadChildrenProfileRequestModel(
    val token: String
)

data class ReadChildrenProfileResponseModel(
    val first_name: String,
    val last_name: String,
    val age: String,
    val photo: String,
    val qr_code: String
)
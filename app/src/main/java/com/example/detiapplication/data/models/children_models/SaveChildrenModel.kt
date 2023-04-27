package com.example.detiapplication.data.models.children_models

data class SaveChildrenToken(
    val token: String
)

data class GetChildrenToken(
    val token: String
)

data class InfoChildrenModel(
    val name: String,
    val last_name: String
)
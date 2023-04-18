package com.example.detiapplication.data.models.parent_models

data class SaveParentToken(
    val token: String
)

data class GetParentToken(
    val token: String
)

data class SaveChildren(
    val flag: Boolean
)

data class GetChildren(
    val flag: Boolean
)
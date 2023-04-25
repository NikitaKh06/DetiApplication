package com.example.detiapplication.presentation.home_models

data class ReadListOfSubjectsRequestModel(
    val token: String,
    val day: String
)

data class ReadListOfSubjectsReceiveModel(
    val id: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String
)
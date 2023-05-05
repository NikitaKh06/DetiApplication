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

data class ReadFullSubjectRequestModel(
    val subject_id: String
)

data class ReadFullSubjectReceiveModel(
    val id: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String,
    val comment: String,
    val homework: String
)

data class AddHomeworkRequestModel(
    val subject_id: String,
    val text: String
)

data class AddCommentRequestModel(
    val subject_id: String,
    val text: String
)

data class AddSubjectRequestModel(
    val token: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String
)
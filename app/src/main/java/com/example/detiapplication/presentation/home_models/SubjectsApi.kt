package com.example.detiapplication.presentation.home_models

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface SubjectsApi {
    @POST("read/subjects_from_children")
    suspend fun readListOfSubjectsFromChildren(@Body request: ReadListOfSubjectsRequestModel) : Response<List<ReadListOfSubjectsReceiveModel>>

    @POST("read/subjects_from_parent")
    suspend fun readListOfSubjectsFromParent(@Body request: ReadListOfSubjectsRequestModel) : Response<List<ReadListOfSubjectsReceiveModel>>

    @POST("read/full_subject")
    suspend fun readFullSubject(@Body request: ReadFullSubjectRequestModel) : Response<ReadFullSubjectReceiveModel>

    @POST("subject/add_homework")
    suspend fun addHomework(@Body request: AddHomeworkRequestModel)

    companion object {

        private var BASE_URL = "https://deti-backend-server.onrender.com/"

        fun create() : SubjectsApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(SubjectsApi::class.java)

        }
    }
}
package com.example.detiapplication.data.repositories.parent_repositories

import com.example.detiapplication.data.models.children_models.ReadChildrenProfileResponseModel
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileRequestModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ParentReadApi {

    @POST("read/parent")
    suspend fun readParentProfile(@Body request: ReadParentProfileRequestModel) : Response<ReadParentProfileResponseModel>

    @POST("read/children_from_parent")
    suspend fun readChildrenProfileFromParent(@Body request: ReadChildrenProfileFromParentRequestModel) : Response<ReadChildrenProfileFromParentResponseModel>

    companion object {

        private var BASE_URL = "https://deti-backend-server.onrender.com/"

        fun create() : ParentReadApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentReadApi::class.java)

        }
    }
}
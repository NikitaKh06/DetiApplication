package com.example.detiapplication.data.repositories.children_repositories

import com.example.detiapplication.data.models.children_models.ReadChildrenProfileResponseModel
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileRequestModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ChildrenReadApi {

    @POST("read/children_from_children")
    suspend fun readChildrenProfile(@Body request: ReadChildrenProfileRequestModel) : Response<ReadChildrenProfileResponseModel>

    companion object {

        private var BASE_URL = "https://deti-backend-server.onrender.com/"

        fun create() : ChildrenReadApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ChildrenReadApi::class.java)

        }
    }
}
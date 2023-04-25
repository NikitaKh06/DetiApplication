package com.example.detiapplication.data.repositories.children_repositories

import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenLoginResponseModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ChildrenRegisterApi {
    @POST("children/register")
    suspend fun registerChildren(@Body children: ChildrenRegistrationRequestModel ) : Response<ChildrenRegistrationResponseModel>

    @POST("children/login")
    suspend fun loginChildren(@Body children: ChildrenLoginRequestModel) : Response<ChildrenLoginResponseModel>

    companion object {

        private var BASE_URL = "https://deti-backend-server.onrender.com/"

        fun create() : ChildrenRegisterApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ChildrenRegisterApi::class.java)

        }
    }
}
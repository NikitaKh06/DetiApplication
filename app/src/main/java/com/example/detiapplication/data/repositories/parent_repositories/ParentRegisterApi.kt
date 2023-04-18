package com.example.detiapplication.data.repositories.parent_repositories

import com.example.detiapplication.domain.models.parent_models.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.net.ssl.SSLEngineResult.Status

interface ParentRegisterApi {
    @POST("parent/register")
    suspend fun registerParent(@Body parent: ParentRegistrationRequestModel) : Response<ParentRegistrationResponseModel>

    @POST("parent/login")
    suspend fun loginParent(@Body parent: ParentLoginRequestModel) : Response<ParentLoginResponseModel>

    @POST("/read/children_by_parent_email")
    suspend fun searchChildren(@Body request: SearchChidlrenRequestModel) : Response<SearchChidldrenResponseModel>

    @POST("/add_children")
    suspend fun addChildren(@Body request: AddChildrenModel) : Response<Boolean?>

    companion object {

        var BASE_URL = "https://deti-backend-server.onrender.com/"

        fun create() : ParentRegisterApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentRegisterApi::class.java)

        }
    }
}
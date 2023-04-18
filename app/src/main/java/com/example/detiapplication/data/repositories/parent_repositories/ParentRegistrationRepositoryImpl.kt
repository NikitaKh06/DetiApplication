package com.example.detiapplication.data.repositories.parent_repositories

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.detiapplication.data.models.parent_models.GetChildren
import com.example.detiapplication.data.models.parent_models.GetParentToken
import com.example.detiapplication.data.models.parent_models.SaveChildren
import com.example.detiapplication.data.models.parent_models.SaveParentToken
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationResponseModel

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_PARENT_TOKEN = "parent_token"

class ParentRegistrationRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(model: SaveParentToken) {
        sharedPreferences.edit().putString(KEY_PARENT_TOKEN, model.token).apply()
    }

    fun getToken() : GetParentToken {
        val token = sharedPreferences.getString(KEY_PARENT_TOKEN, "")
        return GetParentToken(token = token.toString())
    }
}
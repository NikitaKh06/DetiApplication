package com.example.detiapplication.data.repositories.children_repositories

import android.content.Context
import com.example.detiapplication.data.models.children_models.GetChildrenToken
import com.example.detiapplication.data.models.children_models.InfoChildrenModel
import com.example.detiapplication.data.models.children_models.SaveChildrenToken

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_CHILDREN_TOKEN = "children_token"
private const val KEY_NAME_TOKEN = "children_name"
private const val KEY_LASTNAME_TOKEN = "children_last_name"

class ChildrenRegistrationRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(model: SaveChildrenToken) {
        sharedPreferences.edit().putString(KEY_CHILDREN_TOKEN, model.token).apply()
    }

    fun getToken() : GetChildrenToken {
        val token = sharedPreferences.getString(KEY_CHILDREN_TOKEN, "")
        return GetChildrenToken(token = token.toString())
    }

    fun saveInfoChildren(model: InfoChildrenModel) {
        sharedPreferences.edit().putString(KEY_NAME_TOKEN, model.name).apply()
        sharedPreferences.edit().putString(KEY_LASTNAME_TOKEN, model.last_name).apply()
    }

    fun getInfoChildren(): InfoChildrenModel {
        return InfoChildrenModel(
            name = sharedPreferences.getString(KEY_NAME_TOKEN, "")!!,
            last_name = sharedPreferences.getString(KEY_LASTNAME_TOKEN, "")!!
        )
    }

}
package com.example.detiapplication.data.repositories.children_repositories

import android.content.Context
import com.example.detiapplication.data.models.children_models.*

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_CHILDREN_TOKEN = "children_token"
private const val KEY_LOG_IN = "children_log_in"
private const val KEY_TYPE = "type"

class ChildrenRegistrationRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(model: SaveChildrenToken) {
        sharedPreferences.edit().putString(KEY_CHILDREN_TOKEN, model.token).apply()
    }

    fun getToken() : GetChildrenToken {
        val token = sharedPreferences.getString(KEY_CHILDREN_TOKEN, "")
        return GetChildrenToken(token = token.toString())
    }

    fun saveLogInStatus(model: LogInStatusModel) {
        sharedPreferences.edit().putBoolean(KEY_LOG_IN, model.isLogIn).apply()
        sharedPreferences.edit().putString(KEY_TYPE, model.type).apply()
    }

    fun checkLogInStatus() : LogInStatusModel{
        val isLogIn = sharedPreferences.getBoolean(KEY_LOG_IN, false)
        val type = sharedPreferences.getString(KEY_TYPE, "")
        return LogInStatusModel(isLogIn = isLogIn, type = type!!)
    }

    fun clearLogInStatus() {
        sharedPreferences.edit().putBoolean(KEY_LOG_IN, false).apply()
    }
}
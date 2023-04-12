package com.example.detiapplication.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegisterApi
import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var registrationStatus = MutableLiveData<Boolean?>(null)
    var loginStatus = MutableLiveData<Boolean?>(null)

    fun registerChildren(model: ChildrenRegistrationRequestModel) {
        val request = ChildrenRegisterApi.create()
        viewModelScope.launch {
            try {
                Log.d("MyLog", "Start coroutine")
                val response = request.registerChildren(model)
                registrationStatus.value = response.body()?.token?.isNotEmpty() == true
            } catch (_: Exception) {  }
        }

    }

    fun resetRegStatus() {
        registrationStatus.value = null
    }

    fun loginChildren(model: ChildrenLoginRequestModel) {
        val request = ChildrenRegisterApi.create()
        viewModelScope.launch {
            try {
                Log.d("MyLog", "Start coroutine")
                val response = request.loginChildren(model)
                loginStatus.value = response.body()?.token?.isNotEmpty() == true
            } catch (_: Exception) {  }
        }
    }

    fun resetLoginStatus() {
        loginStatus.value = null
    }
}
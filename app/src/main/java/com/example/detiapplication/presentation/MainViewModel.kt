package com.example.detiapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegisterApi
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegisterApi
import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentLoginRequestModel
import com.example.detiapplication.domain.models.parent_models.ParentRegistrationRequestModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var registrationStatus = MutableLiveData<Boolean?>(null)
    var loginStatus = MutableLiveData<Boolean?>(null)
    var registrationStatusParent = MutableLiveData<Boolean?>(null)
    var loginStatusParent = MutableLiveData<Boolean?>(null)

    //Children
    fun registerChildren(model: ChildrenRegistrationRequestModel) {
        val request = ChildrenRegisterApi.create()
        viewModelScope.launch {
            try {
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
                val response = request.loginChildren(model)
                loginStatus.value = response.body()?.token?.isNotEmpty() == true
            } catch (_: Exception) {  }
        }
    }

    fun resetLoginStatus() {
        loginStatus.value = null
    }

    //Parent
    fun registerParent(model: ParentRegistrationRequestModel) {
        val request = ParentRegisterApi.create()
        viewModelScope.launch {
            try {
                val response = request.registerParent(model)
                registrationStatusParent.value = response.body()?.token?.isNotEmpty() == true
            } catch (_: java.lang.Exception) {  }
        }
    }

    fun resetRegStatusParent() {
        registrationStatusParent.value = null
    }

    fun loginParent(model: ParentLoginRequestModel) {
        val request = ParentRegisterApi.create()
        viewModelScope.launch {
            try {
                val response = request.loginParent(model)
                loginStatusParent.value = response.body()?.token?.isNotEmpty() == true
            } catch (_: java.lang.Exception) {  }
        }
    }

    fun resetLoginStatusParent() {
        loginStatusParent.value = null
    }
}
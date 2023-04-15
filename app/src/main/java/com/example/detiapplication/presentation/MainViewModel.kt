package com.example.detiapplication.presentation

import androidx.compose.runtime.mutableStateOf
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
    var childrenRegistrationStatus = MutableLiveData<Boolean?>(null)
    var childrenLoginStatus = MutableLiveData<Boolean?>(null)
    var parentRegistrationStatus = MutableLiveData<Boolean?>(null)
    var parentLoginStatus = MutableLiveData<Boolean?>(null)
    var loadingStatus = mutableStateOf(false)

    //Children
    fun registerChildren(model: ChildrenRegistrationRequestModel) {
        val request = ChildrenRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.registerChildren(model)
                childrenRegistrationStatus.value = response.body()?.token?.isNotEmpty() == true
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }

    }

    fun resetRegStatus() {
        childrenRegistrationStatus.value = null
    }

    fun loginChildren(model: ChildrenLoginRequestModel) {
        val request = ChildrenRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.loginChildren(model)
                childrenLoginStatus.value = response.body()?.token?.isNotEmpty() == true
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun resetLoginStatus() {
        childrenLoginStatus.value = null
    }

    //Parent
    fun registerParent(model: ParentRegistrationRequestModel) {
        val request = ParentRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.registerParent(model)
                parentRegistrationStatus.value = response.body()?.token?.isNotEmpty() == true
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun resetRegStatusParent() {
        parentRegistrationStatus.value = null
    }

    fun loginParent(model: ParentLoginRequestModel) {
        val request = ParentRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.loginParent(model)
                parentLoginStatus.value = response.body()?.token?.isNotEmpty() == true
                loadingStatus.value = false
            } catch (_: java.lang.Exception) {  }
        }
    }

    fun resetLoginStatusParent() {
        parentLoginStatus.value = null
    }
}
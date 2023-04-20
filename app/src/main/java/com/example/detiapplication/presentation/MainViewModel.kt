package com.example.detiapplication.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detiapplication.data.models.parent_models.GetParentToken
import com.example.detiapplication.data.models.parent_models.SaveParentToken
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegisterApi
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegisterApi
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository
import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.domain.models.parent_models.*
import kotlinx.coroutines.launch

class MainViewModel(private val parentRegistrationRepository: ParentRegistrationRepository) : ViewModel() {
    var childrenRegistrationStatus = MutableLiveData<Boolean?>(null)
    var childrenLoginStatus = MutableLiveData<Boolean?>(null)
    var parentRegistrationStatus = MutableLiveData<Boolean?>(null)
    var parentLoginStatus = MutableLiveData<Boolean?>(null)
    val searchChildrenStatus = MutableLiveData<Boolean?>(null)
    var loadingStatus = mutableStateOf(false)
    var responseModel: MutableLiveData<SearchChidldrenResponseModel?> = MutableLiveData(SearchChidldrenResponseModel("", "", ""))
    val addChidlrenStatus = MutableLiveData<Boolean?>(null)

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
                if(response.body()?.token?.isNotEmpty() == true) {
                    parentRegistrationRepository.saveToken(
                        SaveParentToken(token = response.body()?.token!!)
                    )
                }
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
                if(response.body()?.token?.isNotEmpty() == true) {
                    parentRegistrationRepository.saveToken(
                        SaveParentToken(token = response.body()?.token!!)
                    )
                }
                parentLoginStatus.value = response.body()?.token?.isNotEmpty() == true
                loadingStatus.value = false
            } catch (_: java.lang.Exception) {  }
        }
    }

    fun resetLoginStatusParent() {
        parentLoginStatus.value = null
    }

    fun getParentToken() : GetParentToken {
        return parentRegistrationRepository.getToken()
    }

    fun searchChildren(model: SearchChidlrenRequestModel){
        val request = ParentRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                var response = request.searchChildren(model)
                if(response.body() != null) {
                    responseModel.value = SearchChidldrenResponseModel(
                        first_name = response.body()?.first_name.toString(),
                        last_name = response.body()?.last_name.toString(),
                        age = response.body()?.age.toString()
                    )
                    searchChildrenStatus.value = true
                }
                else {
                    searchChildrenStatus.value = false
                }
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun addChildren(model: AddChildrenModel) {
        val request = ParentRegisterApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val respond = request.addChildren(model)
                if (respond.body() == true) {
                    addChidlrenStatus.value = true
                }
                else if(respond.body() == null) {
                    addChidlrenStatus.value = false
                }
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun resetChildrenModel() {
        responseModel.value = SearchChidldrenResponseModel("Ребенок", "не", "найден")
    }

    fun resetAddChildrenStatus() {
        addChidlrenStatus.value = null
    }
}
package com.example.detiapplication.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detiapplication.data.models.children_models.GetChildrenToken
import com.example.detiapplication.data.models.parent_models.GetParentToken
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegistrationRepository
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsReceiveModel
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsRequestModel
import com.example.detiapplication.presentation.home_models.SubjectsApi
import kotlinx.coroutines.launch

class HomeViewModel(
    private val parentRegistrationRepository: ParentRegistrationRepository,
    private val childrenRegistrationRepository: ChildrenRegistrationRepository
    ) : ViewModel() {

    var loadingStatus = mutableStateOf(false)
    var listOfSubjects = MutableLiveData<List<ReadListOfSubjectsReceiveModel>>()

    fun getParentToken() : GetParentToken {
        return parentRegistrationRepository.getToken()
    }

    fun getChildrenToken() : GetChildrenToken {
        return childrenRegistrationRepository.getToken()
    }

    fun readListOfSubjectsFromChildren(model: ReadListOfSubjectsRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readListOfSubjectsFromChildren(model)
                listOfSubjects.value = response.body()
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

}
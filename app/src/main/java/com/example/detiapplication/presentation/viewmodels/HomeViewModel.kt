package com.example.detiapplication.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detiapplication.data.models.children_models.GetChildrenToken
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileResponseModel
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileRequestModel
import com.example.detiapplication.data.models.parent_models.GetParentToken
import com.example.detiapplication.data.repositories.children_repositories.ChildrenReadApi
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegistrationRepository
import com.example.detiapplication.data.repositories.parent_repositories.*
import com.example.detiapplication.presentation.home_models.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val parentRegistrationRepository: ParentRegistrationRepository,
    private val childrenRegistrationRepository: ChildrenRegistrationRepository
    ) : ViewModel() {

    var loadingStatus = mutableStateOf(false)
    var listOfSubjects = MutableLiveData<MutableList<ReadListOfSubjectsReceiveModel>>()
    var fullSubject = MutableLiveData<ReadFullSubjectReceiveModel>()
    var childrenProfile = MutableLiveData<ReadChildrenProfileResponseModel>()
    var parentProfile = MutableLiveData<ReadParentProfileResponseModel>()
    var childrenProfileFromParent = MutableLiveData<ReadChildrenProfileFromParentResponseModel>()
    val calendar = Calendar.getInstance()
    val time = calendar.time
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH).format(time.time)
    var weekDay = MutableLiveData(dayOfWeek)

    fun changeDay(day: String) {
        weekDay.value = day
    }

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
                listOfSubjects.value = response.body()?.toMutableList()
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun readListOfSubjectsFromParent(model: ReadListOfSubjectsRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readListOfSubjectsFromParent(model)
                listOfSubjects.value = response.body()?.toMutableList()
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun cleanList() {
        listOfSubjects.value?.clear()
    }

    fun readFullSubject(model: ReadFullSubjectRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readFullSubject(model)
                if(response.body()?.comment?.isEmpty() == true) {
                    fullSubject.value = ReadFullSubjectReceiveModel(
                        id = response.body()!!.id,
                        title = response.body()!!.title,
                        day = response.body()!!.day,
                        hours = response.body()!!.hours,
                        minutes = response.body()!!.minutes,
                        comment = "Комментариев нет",
                        homework = response.body()!!.homework
                    )
                }
                else {
                    fullSubject.value = response.body()
                }
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun addHomework(model: AddHomeworkRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.addHomework(model)
            } catch (_: Exception) {  }
            loadingStatus.value = false
        }
    }

    fun addComment(model: AddCommentRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.addComment(model)
            } catch (_: Exception) {  }
            loadingStatus.value = false
        }
    }

    fun addSubject(model: AddSubjectRequestModel) {
        val request = SubjectsApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.addSubject(model)
            } catch (_: Exception) {  }
            loadingStatus.value = false
        }
    }

    fun readChildrenProfile(model: ReadChildrenProfileRequestModel) {
        val request = ChildrenReadApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readChildrenProfile(model)
                childrenProfile.value = response.body()
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun readParentProfile(model: ReadParentProfileRequestModel) {
        val request = ParentReadApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readParentProfile(model)
                parentProfile.value = response.body()
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }

    fun readChildrenProfileFromParent(model: ReadChildrenProfileFromParentRequestModel) {
        val request = ParentReadApi.create()
        loadingStatus.value = true
        viewModelScope.launch {
            try {
                val response = request.readChildrenProfileFromParent(model)
                if(response.body() != null) {
                    childrenProfileFromParent.value = response.body()
                }
                loadingStatus.value = false
            } catch (_: Exception) {  }
        }
    }
}
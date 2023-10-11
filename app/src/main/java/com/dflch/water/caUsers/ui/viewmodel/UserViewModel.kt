package com.dflch.water.caUsers.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caUsers.data.UserRepository
import com.dflch.water.caUsers.domain.AddUserUseCase
import com.dflch.water.caUsers.domain.GetUsersAPIUseCase
import com.dflch.water.caUsers.domain.GetUsersUseCase
import com.dflch.water.caUsers.ui.UserUiState
import com.dflch.water.caUsers.ui.UserUiState.Success
import com.dflch.water.caUsers.ui.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase,
    private val getUsersAPIUseCase: GetUsersAPIUseCase,
    private val addUserUseCase: AddUserUseCase,

) : ViewModel() {

    val uiState: StateFlow<UserUiState> = getUsersUseCase().map(::Success)
        .catch { UserUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserUiState.Loading)

    private val _users = mutableStateListOf<UserModel>()
    private val user: List<UserModel> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    fun onCreate() {
        viewModelScope.launch {
            //isLoading.postValue(true)
            _isLoading.value = true

            val result = user
            //if (!result.isNullOrEmpty()) {
            //    userModel.postValue(result[0])
                //isLoading.postValue(false)
            //}
            
            //addUserUseCase(UserModel(0,0,0,"","","",true,true, true,true))

            onUsersCreate()

            _isLoading.value = false
        }
    }

    private fun onUsersCreate() {

        viewModelScope.launch {
            //getUsersAPIUseCase()

            try {
                getUsersAPIUseCase()
                _status.value = "Success"

            } catch (e: Exception) {
                _status.value = "Exception: ${e.message}"

            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                _status.value = "HttpException: ${e.message}"

            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                _status.value = "IOException: ${e.message}"
            }

        }
    }

}








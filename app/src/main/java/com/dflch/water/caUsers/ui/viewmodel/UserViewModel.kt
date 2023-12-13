package com.dflch.water.caUsers.ui.viewmodel

import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dflch.water.caFoto.domain.FotoUseCase
import com.dflch.water.caFoto.ui.model.FotoModel
import com.dflch.water.caUsers.domain.GetUserUseCase
import com.dflch.water.caUsers.domain.GetUsersAPIUseCase
import com.dflch.water.caUsers.domain.GetUsersUseCase
import com.dflch.water.caUsers.domain.UserOKUseCase
import com.dflch.water.caUsers.ui.UserUiState
import com.dflch.water.caUsers.ui.UserUiState.Success
import com.dflch.water.caUsers.ui.model.UserModel
import com.dflch.water.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val userOKUseCase: UserOKUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val fotoUseCase: FotoUseCase

) : ViewModel() {

    private val _idUser = MutableLiveData<String>()
    val idUser: LiveData<String> = _idUser

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _apellido = MutableLiveData<String>()
    val apellido: LiveData<String> = _apellido

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    val uiState: StateFlow<UserUiState> = getUsersUseCase().map(::Success)
        .catch { UserUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserUiState.Loading)

    private val _users = mutableStateListOf<UserModel>()
    private val user: List<UserModel> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isErrorLogin = MutableLiveData<Boolean>()
    val isErrorLogin: LiveData<Boolean> = _isErrorLogin

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    //Ver la foto del colaborador
    private val _base64 = MutableLiveData<String>()
    val base64: LiveData<String> = _base64

    fun onCreate() {
        viewModelScope.launch {

            _isLoading.value = true
            val result = user
            _isLoading.value = false

            _status.value = "Success"

            try {
                getUsersAPIUseCase()
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

    fun onLoginChanged(
        idUser: String,
        password: String
    ){
        _idUser.value = idUser
        _password.value = password
        _isLoginEnable.value =  enableLogin(idUser, password)
    }

    fun enableLogin(idUser: String, password: String): Boolean = (idUser.length >= 2) && (password.length >= 2)

    fun onLoginSelectec(navController: NavController) {
        viewModelScope.launch {

            _isErrorLogin.value = false

            val result = userOKUseCase.doLogin(idUser.value!!.toInt(), password.value!!)

            if (result > 0) {

                viewModelScope.launch {
                    val user: List<UserModel> = getUserUseCase(idUser.value!!.toInt())

                    for (u in user) {
                        _idUser.value = u.ter_num_id.toString()
                        _nombre.value = u.ter_nombre
                        _apellido.value = u.ter_apellido
                    }

                    val foto: List<FotoModel> = fotoUseCase.colaboradorID(_idUser.value.toString())
                    for (f in foto) {
                        _base64.value = f.foto
                    }
                }

                try {
                    //navController.navigate(AppScreens.MenuScreen.route)
                    navController.navigate(AppScreens.MainScreen.route)
                } catch (e: Exception) {
                    Toast
                        .makeText(navController.context, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                    _isErrorLogin.value = true
                }

            } else {
                _isErrorLogin.value = true
            }
        }
    }
}








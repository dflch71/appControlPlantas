package com.dflch.water.caUsers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caUsers.domain.GetUsersAPIUseCase
import com.dflch.water.caUsers.ui.UserUiState
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
class SplashViewModel @Inject constructor(
    private val getUsersAPIUseCase: GetUsersAPIUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)

            try {
                getUsersAPIUseCase()

                } catch (e: Exception) {
                    _state.value = UiState( status = "Exception: ${e.message}")
                } catch (e: HttpException) {
                    _state.value = UiState( status= "HttpException: ${e.message}")
                } catch (e: IOException) {
                    _state.value = UiState( status= "IOException: ${e.message}")
            }

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val status: String = "Success",
        val users: List<UserModel> = emptyList()
    )

}
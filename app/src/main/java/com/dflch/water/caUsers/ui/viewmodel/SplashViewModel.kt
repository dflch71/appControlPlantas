package com.dflch.water.caUsers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caUsers.domain.GetUsersAPIUseCase
import com.dflch.water.caUsers.domain.MergeUserUseCase
import com.dflch.water.caUsers.ui.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUsersAPIUseCase: GetUsersAPIUseCase,
    private val mergeUserUseCase: MergeUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {

            _state.value = UiState(loading = true)

            try {
                //getUsersAPIUseCase()
                mergeUserUseCase()

                } catch (e: Exception) {
                    _state.value = UiState( status = "Exception: ${e.message}")
                } catch (e: HttpException) {
                    _state.value = UiState( status = "HttpException: ${e.message}")
                } catch (e: IOException) {
                    _state.value = UiState( status = "IOException: ${e.message}")
            }

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val status: String = "Success",
        val users: List<UserModel> = emptyList()
    )

}
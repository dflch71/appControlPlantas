package com.dflch.water.caUsers.ui

import com.dflch.water.caUsers.ui.model.UserModel

sealed interface UserUiState {
    object Loading: UserUiState
    data class Error(val throwable: Throwable): UserUiState
    data class Success(val users: List<UserModel>): UserUiState
}

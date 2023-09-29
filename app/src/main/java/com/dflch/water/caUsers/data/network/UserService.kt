package com.dflch.water.caUsers.data.network

import com.dflch.water.caUsers.data.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApiClient) {

    suspend fun getUsers(): List<UserResponse> {
        return withContext(Dispatchers.IO) {

                val response = api.getAllUsers()
                response.body() ?: emptyList()

        }
    }

}
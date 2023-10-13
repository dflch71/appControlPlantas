package com.dflch.water.caUsers.domain

import com.dflch.water.caUsers.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserOKUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun doLogin(numID: Int, password: String): Int {
        return withContext(Dispatchers.IO) {
            userRepository.userOk(numID, password)
        }
    }


}
package com.dflch.water.caUsers.domain

import com.dflch.water.caUsers.data.UserRepository
import com.dflch.water.caUsers.data.database.entities.toDatabase
import com.dflch.water.caUsers.ui.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(numID: Int): List<UserModel> {

        return withContext(Dispatchers.IO) {
//            userRepository.userOk(numID, password)
            userRepository.userID(numID)
        }

    }

}
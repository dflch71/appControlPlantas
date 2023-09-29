package com.dflch.water.caUsers.domain

import com.dflch.water.caUsers.data.UserRepository
import com.dflch.water.caUsers.ui.model.UserModel
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(userModel: UserModel) {
        userRepository.updateUsers(userModel)
    }

}
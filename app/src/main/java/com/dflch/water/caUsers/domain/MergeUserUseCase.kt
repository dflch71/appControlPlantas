package com.dflch.water.caUsers.domain

import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caUsers.data.UserRepository
import javax.inject.Inject

class MergeUserUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke() {
        userRepository.requestUsers()
    }

}
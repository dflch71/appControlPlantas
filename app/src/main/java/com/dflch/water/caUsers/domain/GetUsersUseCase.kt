package com.dflch.water.caUsers.domain

import com.dflch.water.caUsers.data.UserRepository
import com.dflch.water.caUsers.data.database.entities.toDatabase
import com.dflch.water.caUsers.ui.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {

    // Funcion que consulta API y llena la BD Local
    /*
    suspend operator fun invoke():List<UserModel>{
        val users = repository.getAllUserFromApi()

        return if(users.isNotEmpty()){
            repository.clearUsers()
            repository.insertUsers(users.map { it.toDatabase() })
            users
        } else {
            repository.getAllUserFromDatabase()
        }
    }
    */

    operator fun invoke(): Flow<List<UserModel>> = userRepository.users
}
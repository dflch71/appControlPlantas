package com.dflch.water.caUsers.domain


import com.dflch.water.caUsers.data.UserRepository
import com.dflch.water.caUsers.ui.model.UserModel
import javax.inject.Inject


/*
Consulta los usuarios de la API, si exite update else insert
*/


class GetUsersAPIUseCase @Inject constructor(private val userRepository: UserRepository) {

    // Funcion que consulta API y llena la BD Local
    suspend operator fun invoke(){

        val users = userRepository.getAllUserFromApi()

        if (users.isNotEmpty()){

            //userRepository.deleteAllUsers()

            for (u in users) {

                if (userRepository.userExiste(u.ter_num_id) == 0) {
                    userRepository.addUsers(
                        UserModel(
                            u.id,
                            u.ter_id,
                            u.ter_num_id,
                            u.ter_nombre,
                            u.ter_apellido,
                            u.ter_clave,
                            u.ter_activo,
                            u.ter_tecnica_plantas,
                            u.ter_tecnica_sql,
                            u.ter_tecnica_valvulas
                        ))
                } else {
                    userRepository.updateUsers(
                        UserModel(
                            u.id,
                            u.ter_id,
                            u.ter_num_id,
                            u.ter_nombre,
                            u.ter_apellido,
                            u.ter_clave,
                            u.ter_activo,
                            u.ter_tecnica_plantas,
                            u.ter_tecnica_sql,
                            u.ter_tecnica_valvulas
                        ))
                }
            }
        } else { userRepository.users }
    }
}


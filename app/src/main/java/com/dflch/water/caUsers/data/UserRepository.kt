package com.dflch.water.caUsers.data


import com.dflch.water.caUsers.data.database.dao.UserDao
import com.dflch.water.caUsers.data.database.entities.UserEntity
import com.dflch.water.caUsers.data.database.entities.toDatabase
import com.dflch.water.caUsers.data.model.UserResponse
import com.dflch.water.caUsers.data.network.UserService
import com.dflch.water.caUsers.ui.model.UserModel
import com.dflch.water.caUsers.ui.model.toDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    private val api: UserService,
    private val userDao: UserDao
) {

    suspend fun getAllUserFromApi(): List<UserModel> {
        val response: List<UserResponse> = api.getUsers()
        return response.map { it.toDomain() }
    }


    //Crear variable del objeto para cargar los usuarios
    val users: Flow<List<UserModel>> =
        userDao.getUsers().map {
                items -> items.map {
                    it.toDomain()
                      /*UserModel(
                        it.id,
                        it.ter_id,
                        it.ter_num_id,
                        it.ter_nombre,
                        it.ter_apellido,
                        it.ter_clave,
                        it.ter_activo,
                        it.ter_tecnica_plantas,
                        it.ter_tecnica_sql,
                        it.ter_tecnica_valvulas
                    )*/
                }
        }

    suspend fun userID(numID: Int) : List<UserModel> {

        return userDao.getUser(numID).map { it.toDomain() }
        /*
        return userDao.getUser(numID).map { items ->
            items.map {
                it.toDomain()
            }
        }
        */
    }



    /*
    suspend fun getAllUserFromDatabase(): List<UserModel> {
        val response: Flow<List<UserEntity>> = userDao.getUsers()
        return response.map { it.toDomain() }
    }
    */

    suspend fun addUsers(userModel: UserModel) {
//        userDao.addUser(
//            UserEntity(
//                userModel.id,
//                userModel.ter_id,
//                userModel.ter_num_id,
//                userModel.ter_nombre,
//                userModel.ter_apellido,
//                userModel.ter_clave,
//                userModel.ter_activo,
//                userModel.ter_tecnica_plantas,
//                userModel.ter_tecnica_sql,
//                userModel.ter_tecnica_valvulas
//            )
//        )
        userDao.addUser(userModel.toData())
    }

    suspend fun updateUsers(userModel: UserModel) {
        userDao.updateUser(userModel.toData())
    }

    suspend fun deleteUsers(userModel: UserModel) {
        userDao.deleteUser(userModel.toData())
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun insertAll(userModel: List<UserModel>) {
        userDao.insertAll(userModel.map { it -> it.toDatabase() })
    }

    suspend fun userExiste(numID: Int) : Int {
        return userDao.getUserById(numID)
    }

    fun userOk(numID: Int, terClave: String): Int {
        return userDao.getUserPassword(numID, terClave)
    }


}

fun UserModel.toData(): UserEntity{
    return UserEntity(
        this.id,
        this.ter_id,
        this.ter_num_id,
        this.ter_nombre,
        this.ter_apellido,
        this.ter_clave,
        this.ter_activo,
        this.ter_tecnica_plantas,
        this.ter_tecnica_sql,
        this.ter_tecnica_valvulas
    )
}
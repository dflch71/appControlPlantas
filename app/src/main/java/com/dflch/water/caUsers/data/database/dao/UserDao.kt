package com.dflch.water.caUsers.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dflch.water.caUsers.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getUsers(): Flow<List<UserEntity>>

    @Insert
    suspend fun addUser(item: UserEntity)

    @Update
    suspend fun updateUser(item: UserEntity)

    @Delete
    suspend fun deleteUser(item: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users : List<UserEntity>)

    @Query("SELECT COUNT(*) FROM UserEntity WHERE ter_num_id = :numID")
    suspend fun getUserById(numID: Int): Int

    @Query("SELECT COUNT(*) FROM UserEntity WHERE ter_num_id = :numID and ter_clave = :terClave")
    fun getUserPassword(numID: Int, terClave: String): Int


}
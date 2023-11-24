package com.dflch.water.caUsers.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caUsers.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE ter_num_id = :numID")
    suspend fun getUser(numID: Int): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(item: UserEntity)

    @Update
    suspend fun updateUser(item: UserEntity)

    @Delete
    suspend fun deleteUser(item: UserEntity)

    @Upsert
    suspend fun upsertUser(items: UserEntity)

    @Upsert
    suspend fun upsertAllUsers(items: List<UserEntity>)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAllUsers()

    @Query("DELETE FROM UserEntity WHERE ter_id NOT IN (:terID)")
    suspend fun deleteUserList(terID: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users : List<UserEntity>)

    @Query("SELECT COUNT(*) FROM UserEntity WHERE ter_num_id = :numID")
    suspend fun getUserById(numID: Int): Int

    @Query("SELECT COUNT(*) FROM UserEntity WHERE ter_num_id = :numID and ter_clave = :terClave")
    fun getUserPassword(numID: Int, terClave: String): Int

    @Query("SELECT COUNT(*) FROM UserEntity")
    suspend fun countUsers(): Int



}
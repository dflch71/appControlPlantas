package com.dflch.water.caTurnos.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import kotlinx.coroutines.flow.Flow

interface TurnoDao {
    @Query("SELECT * FROM TurnoEntity")
    fun getAllTurnos() : Flow<List<TurnoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTurnos(turnos: List<TurnoEntity>)

    @Query("SELECT * FROM TurnoEntity WHERE id = :id")
    fun getTurnoById(id: Long): List<TurnoEntity>

    @Query("SELECT * FROM TurnoEntity WHERE tur_id = :tur_id")
    fun getTurnoByNumID(tur_id: Int): List<TurnoEntity>

    @Query("SELECT * FROM TurnoEntity WHERE tur_id = :tur_id")
    fun getTurnoNumID(tur_id: Int): TurnoEntity

    @Query("SELECT * FROM TurnoEntity WHERE time(current_time, 'localtime') BETWEEN tur_inicia AND tur_finaliza")
    fun getTurnoActivo(): TurnoEntity

    @Query("DELETE FROM TurnoEntity")
    suspend fun deleteAllTurno()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTurno(TurnoEntity: TurnoEntity): Long

    @Update
    suspend fun updateTurno(TurnoEntity: TurnoEntity): Int

    @Delete
    suspend fun deleteTurno(TurnoEntity: TurnoEntity): Int

    @Query("UPDATE TurnoEntity SET tur_inicia = :tur_inicia," +
           "tur_finaliza = :tur_finaliza, tur_nombre = :tur_nombre "+
           "WHERE tur_id = :tur_id")
    suspend fun updateTurnoSQL(
        tur_inicia: String,
        tur_finaliza: String,
        tur_nombre: String,
        tur_id: Int) : Int


    @Query("SELECT COUNT(*) FROM TurnoEntity")
    suspend fun countTurnos(): Int
}
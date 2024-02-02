package com.dflch.water.caPlantillas.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dflch.water.caPlantillas.data.database.entities.PlantillaEntity
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface PlantillaDao {
    @Query("SELECT * FROM PlantillaEntity WHERE smt_id NOT IN (5,6)")
    fun getAllPlantillas() : Flow<List<PlantillaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlantillas(plantillas: List<PlantillaEntity>)

    @Query("SELECT * FROM PlantillaEntity where id = :id ORDER BY smt_id")
    fun getPlantillaById(id: Long): List<PlantillaEntity>

    @Query("SELECT * FROM PlantillaEntity where plt_id = :plt_id ORDER BY smt_id")
    fun getPlantillaByNumID(plt_id: Int): List<PlantillaEntity>

    @Query("SELECT * FROM PlantillaEntity where plt_id = :plt_id ORDER BY smt_id")
    fun getPlantillaNumID(plt_id: Int): PlantillaEntity

    @Query("SELECT * FROM PlantillaEntity where pls_id = :pls_id ORDER BY smt_id")
    fun getPlantillaID(pls_id: Int): List<PlantillaEntity>

    @Query("DELETE FROM PlantillaEntity")
    suspend fun deleteAllPlantilla()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlantilla(PlantillaEntity: PlantillaEntity): Long

    @Update
    suspend fun updatePlantilla(PlantillaEntity: PlantillaEntity): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllPlantillas(plantillas: List<PlantillaEntity>)

    @Delete
    suspend fun deletePlantilla(PlantillaEntity: PlantillaEntity): Int

    @Query("UPDATE PlantillaEntity SET plt_id = :plt_id," +
            "smt_id = :smt_id, plantilla = :plantilla,"+
            "sitio = :sitio WHERE pls_id = :pls_id")
    suspend fun updatePlantillaSQL(
        plt_id: Int,
        smt_id: Int,
        pls_id: Int,
        plantilla: String,
        sitio: String
        ) : Int


    @Query("SELECT COUNT(*) FROM PlantillaEntity")
    suspend fun countPlantillas(): Int
}
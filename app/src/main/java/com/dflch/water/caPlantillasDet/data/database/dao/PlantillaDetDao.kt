package com.dflch.water.caPlantillasDet.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caPlantillas.data.database.entities.PlantillaEntity
import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.data.model.LugaresMuestra
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantillaDetDao {
    @Query("SELECT * FROM PlantillaDetEntity Order by plt_id, pld_orden, lug_id")
    fun getAllPlantillaDet() : Flow<List<PlantillaDetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlantillasDet(plantillasDet: List<PlantillaDetEntity>)

    @Query("SELECT DISTINCT(lug_nombre), plt_id FROM PlantillaDetEntity")
    fun getLugaresMuestra(): Flow<List<LugaresMuestra>>

    //Consultar las variables que tengan valores
    @Query("SELECT * FROM PlantillaDetEntity WHERE ltc_fecha_hora <> '' and car_exportado = 0 ")
    fun getLecturasEnviarSQL(): Flow<List<PlantillaDetEntity>>


    @Query("DELETE FROM PlantillaDetEntity")
    suspend fun deleteAllPlantillaDet()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlantillaDet(PlantillaDetEntity: PlantillaDetEntity): Long

    @Update
    suspend fun updatePlantillaDet(PlantillaDetEntity: PlantillaDetEntity): Int

    @Delete
    suspend fun deletePlantillaDet(PlantillaDetEntity: PlantillaDetEntity): Int

    @Query("UPDATE PlantillaDetEntity SET  plt_id = :plt_id, lug_id = :lug_id," +
            "lug_nombre = :lug_nombre, pld_orden = :pld_orden, car_id = :car_id, car_nombre = :car_nombre,"+
            "car_expresado = :car_expresado, car_unidad = :car_unidad, car_vrMin = :car_vrMin, car_vrMax = :car_vrMax,"+
            "car_lectura = :car_lectura, car_exportado = :car_exportado WHERE pld_id = :pld_id")
    suspend fun updatePlantillaDetSQL(
        plt_id: Int,
        lug_id: Int,
        lug_nombre: String,
        pld_orden: Int,
        car_id: Int,
        car_nombre: String,
        car_expresado: String,
        car_unidad: String,
        car_vrMin: Float,
        car_vrMax: Float,
        car_lectura: Float,
        car_exportado: Boolean,
        pld_id: Int) : Int

    @Query("UPDATE PlantillaDetEntity SET car_lectura = :car_lectura, car_exportado = :car_exportado, smt_id = :smt_id, ter_id = :ter_id, tur_id = :tur_id," +
            "ltc_altitud = :ltc_altitud, ltc_latitud = :ltc_latitud, ltc_longitud = :ltc_longitud, ltc_fecha_hora = :ltc_fecha_hora WHERE pld_id = :pld_id")
    suspend fun updatePlantillaDetLecturaSQL(
        car_lectura: Float,
        car_exportado: Boolean,
        smt_id: Int,
        ter_id: Int,
        tur_id: Int,
        ltc_altitud: Float,
        ltc_latitud: Float,
        ltc_longitud: Float,
        ltc_fecha_hora: String,
        pld_id: Int) : Int

    @Query("UPDATE PlantillaDetEntity SET car_lectura = :car_lectura, car_exportado = :car_exportado, smt_id = :smt_id, ter_id = :ter_id, tur_id = :tur_id," +
            "ltc_altitud = :ltc_altitud, ltc_latitud = :ltc_latitud, ltc_longitud = :ltc_longitud, ltc_fecha_hora = :ltc_fecha_hora")
    suspend fun updatePlantillaDetLecturaCero(
        car_lectura: Float,
        car_exportado: Boolean,
        smt_id: Int,
        ter_id: Int,
        tur_id: Int,
        ltc_altitud: Float,
        ltc_latitud: Float,
        ltc_longitud: Float,
        ltc_fecha_hora: String) : Int

    @Query("UPDATE PlantillaDetEntity SET car_exportado = :car_exportado WHERE pld_id = :pld_id")
    suspend fun updatePlantillaSend(
        car_exportado: Boolean,
        pld_id: Int) : Int

    @Query("SELECT COUNT(*) FROM PlantillaDetEntity")
    suspend fun countPlantillasDet(): Int

    @Query("UPDATE PlantillaDetEntity SET car_lectura = :car_lectura, car_exportado = :car_exportado WHERE pld_id = :pld_id")
    suspend fun updateLecturaDet(
        car_exportado: Boolean,
        car_lectura: Float,
        pld_id: Int) : Int

    @Update
    suspend fun updatePlantillaDetLectura(plantillaDetEntity: PlantillaDetEntity)


}
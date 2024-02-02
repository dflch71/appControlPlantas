package com.dflch.water.Plantillas.data

import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.dflch.water.caPlantillas.data.database.dao.PlantillaDao
import com.dflch.water.caPlantillas.data.database.entities.PlantillaEntity
import com.dflch.water.caPlantillas.data.model.Plantilla
import com.dflch.water.caPlantillas.data.network.PlantillaService
import com.dflch.water.caPlantillas.ui.model.PlantillaModel
import com.dflch.water.caPlantillas.ui.model.toDomain
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import com.dflch.water.caTurnos.ui.model.TurnoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantillaRepository @Inject constructor(
    private val api: PlantillaService,
    private val plantillaDao: PlantillaDao
) {

    suspend fun getAllPlantillasFromApi(): List<PlantillaModel> {
        val response: List<Plantilla> = api.getAllPlantillas()
        return response.map { it.toDomain() }
    }

    val plantillas: Flow<List<PlantillaModel>> =
        plantillaDao.getAllPlantillas().map {
                items -> items.map {
                    it.toDomain()
                }
        }

    suspend fun addPlantilla(plantillaModel: PlantillaModel) {
        plantillaDao.addPlantilla(plantillaModel.toData())
    }

    suspend fun updatePlantilla(plantillaModel: PlantillaModel) {
        plantillaDao.updatePlantilla(plantillaModel.toData())
    }

    suspend fun deletePlantilla(plantillaModel: PlantillaModel) {
        plantillaDao.deletePlantilla(plantillaModel.toData())
    }

    suspend fun deleteAllPlantillas() {
        plantillaDao.deleteAllPlantilla()
    }

    suspend fun insertAllPlantillas(plantillas: List<PlantillaModel>) {
        plantillaDao.insertAllPlantillas(plantillas.map { it.toData() })
    }

    suspend fun updatetAllPlantillas(plantillas: List<PlantillaModel>) {
        for (p in plantillas) {
            plantillaDao.updatePlantillaSQL(
                p.plt_id,
                p.smt_id,
                p.pls_id,
                p.plantilla,
                p.sitio
            )
        }
    }

    suspend fun requestPlantillas(){
        val isDBEmpty = plantillaDao.countPlantillas() == 0
        if (isDBEmpty){
            //Insertar los items
            insertAllPlantillas(getAllPlantillasFromApi())
        } else {
            updatetAllPlantillas(getAllPlantillasFromApi())
        }
    }

    private fun PlantillaModel.toData(): PlantillaEntity {
        return PlantillaEntity(
            0,
            this.pls_id,
            this.plt_id,
            this.smt_id,
            this.sitio,
            this.plantilla
        )
    }    

}
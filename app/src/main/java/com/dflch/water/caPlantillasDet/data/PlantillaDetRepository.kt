package com.dflch.water.PlantillasDet.data

import com.dflch.water.caPlantillasDet.data.database.dao.PlantillaDetDao
import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.data.model.PlantillaDet
import com.dflch.water.caPlantillasDet.data.network.PlantillaDetService
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caPlantillasDet.ui.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantillaDetRepository @Inject constructor(
    private val api: PlantillaDetService,
    private val plantillaDetDao: PlantillaDetDao
) {

    suspend fun getAllPlantillasDetFromApi(): List<PlantillaDetModel> {
        val response: List<PlantillaDet> = api.getAllPlantillasDet()
        return response.map { it.toDomain() }
    }

    val plantillas: Flow<List<PlantillaDetModel>> =
        plantillaDetDao.getAllPlantillaDet().map {
                items -> items.map {
                    it.toDomain()
                }
        }

    suspend fun addPlantillaDet(plantillaDetModel: PlantillaDetModel) {
        plantillaDetDao.addPlantillaDet(plantillaDetModel.toData())
    }

    suspend fun updatePlantillaDet(plantillaDetModel: PlantillaDetModel) {
        plantillaDetDao.updatePlantillaDet(plantillaDetModel.toData())
    }

    suspend fun deletePlantillaDet(plantillaDetModel: PlantillaDetModel) {
        plantillaDetDao.deletePlantillaDet(plantillaDetModel.toData())
    }

    suspend fun deleteAllPlantillas() {
        plantillaDetDao.deleteAllPlantillaDet()
    }

    suspend fun insertAllPlantillasDet(plantillasDet: List<PlantillaDetModel>) {
        plantillaDetDao.insertAllPlantillasDet(plantillasDet.map { it.toData() })
    }

    suspend fun updatetAllPlantillasDet(plantillasDet: List<PlantillaDetModel>) {
        for (p in plantillasDet) {
            plantillaDetDao.updatePlantillaDetSQL(
                p.plt_id,
                p.lug_id,
                p.lug_nombre,
                p.pld_orden,
                p.car_id,
                p.car_nombre,
                p.car_expresado,
                p.car_unidad,
                p.car_vrMin.toFloat(),
                p.car_vrMax.toFloat(),
                0f,
                false,
                p.pld_id
            )
        }
    }

    suspend fun requestPlantillasDet(){
        val isDBEmpty = plantillaDetDao.countPlantillasDet() == 0
        if (isDBEmpty){
            //Insertar los items
            insertAllPlantillasDet(getAllPlantillasDetFromApi())
        } else {
            updatetAllPlantillasDet(getAllPlantillasDetFromApi())
        }
    }

    private fun PlantillaDetModel.toData(): PlantillaDetEntity {
        return PlantillaDetEntity(
            0,
            this.plt_id,
            this.pld_id,
            this.lug_id,
            this.lug_nombre,
            this.pld_orden,
            this.car_id,
            this.car_nombre,
            this.car_expresado,
            this.car_unidad,
            this.car_vrMin.toFloat(),
            this.car_vrMax.toFloat(),
            0f,
            false,
            0,
            0,
            0,
            0f,
            0f,
            0f,
            ""
        )
    }    

}
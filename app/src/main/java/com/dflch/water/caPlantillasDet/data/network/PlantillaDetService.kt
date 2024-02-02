package com.dflch.water.caPlantillasDet.data.network

import android.util.Log
import com.dflch.water.caPlantillasDet.data.model.PlantillaDet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PlantillaDetService @Inject constructor(private val api: PlantillaDetApiClient) {

    suspend fun getAllPlantillasDet(): List<PlantillaDet> {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getAllPlantillasDet()
                response.plantillaDet ?: emptyList()
            }
        }  catch (e: Exception) {
            Log.e("PlantillasService", "getAllPlantillas", e)
            emptyList()
        }
    }

}
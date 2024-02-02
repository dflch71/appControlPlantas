package com.dflch.water.caPlantillas.data.network

import android.util.Log
import com.dflch.water.caPlantillas.data.model.Plantilla
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PlantillaService @Inject constructor(private val api: PlantillaApiClient) {

    suspend fun getAllPlantillas(): List<Plantilla> {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getAllPlantillas()
                response.plantillas ?: emptyList()
            }
        }  catch (e: Exception) {
            Log.e("PlantillasService", "getAllPlantillas", e)
            emptyList()
        }
    }

}
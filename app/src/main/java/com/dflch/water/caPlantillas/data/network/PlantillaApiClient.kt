package com.dflch.water.caPlantillas.data.network

import com.dflch.water.caPlantillas.data.model.PlantillaResponse
import com.dflch.water.utils.Constants
import retrofit2.http.GET

interface PlantillaApiClient {

    @GET(Constants.GET_PATH_PLANTILLAS)
    suspend fun getAllPlantillas(): PlantillaResponse
}
package com.dflch.water.caPlantillasDet.data.network

import com.dflch.water.caPlantillasDet.data.model.PlantillaDetResponse
import com.dflch.water.utils.Constants
import retrofit2.http.GET

interface PlantillaDetApiClient {

    @GET(Constants.GET_PATH_PLANTILLA_DET)
    suspend fun getAllPlantillasDet(): PlantillaDetResponse
}
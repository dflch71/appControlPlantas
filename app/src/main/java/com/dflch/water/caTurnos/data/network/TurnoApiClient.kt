package com.dflch.water.caTurnos.data.network

import com.dflch.water.caTurnos.data.model.TurnoResponse
import com.dflch.water.utils.Constants
import retrofit2.http.GET

interface TurnoApiClient {

    @GET(Constants.GET_PATH_TURNOS)
    suspend fun getAllTurnos(): TurnoResponse
}
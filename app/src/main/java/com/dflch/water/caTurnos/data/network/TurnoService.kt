package com.dflch.water.caTurnos.data.network

import android.util.Log
import com.dflch.water.caTurnos.data.model.Turno
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TurnoService @Inject constructor(private val api: TurnoApiClient) {

    suspend fun getAllTurnos(): List<Turno> {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getAllTurnos()
                response.turnos ?: emptyList()
            }
        }  catch (e: Exception) {
            Log.e("TurnoService", "getAllTurnos", e)
            emptyList()
        }
    }

}
package com.dflch.water.caFoto.data.network

import com.dflch.water.caFoto.data.model.FotoColaborador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FotoService @Inject constructor(private val api: FotoApiClient) {

    suspend fun getColaborador(numID: String): List<FotoColaborador> {
        return try {

            withContext(Dispatchers.IO) {
                val response = api.getColaborador(numID)
                response.fotoColaborador ?: emptyList()
            }
        }  catch (e: Exception) { emptyList() }
    }

}


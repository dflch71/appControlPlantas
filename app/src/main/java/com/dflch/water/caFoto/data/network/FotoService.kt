package com.dflch.water.caFoto.data.network

import com.dflch.water.caFoto.data.model.FotoColaborador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FotoService @Inject constructor(private val api: ColaboradorApiClient) {

    suspend fun getColaborador(id: String): List<FotoColaborador> {
        return withContext(Dispatchers.IO) {
            val response = api.getColaborador(id)
            response.fotoColaborador ?: emptyList()
        }
    }

}


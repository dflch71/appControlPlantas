package com.dflch.water.caFoto.domain

import com.dflch.water.caFoto.data.FotoRepository
import com.dflch.water.caFoto.ui.model.FotoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FotoUseCase @Inject constructor(private val fotoRepository: FotoRepository) {

    suspend fun colaboradorID(numID: String): List<FotoModel> {

        return try {
            withContext(Dispatchers.IO) {
                fotoRepository.getColaboradorFromApi(numID)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}
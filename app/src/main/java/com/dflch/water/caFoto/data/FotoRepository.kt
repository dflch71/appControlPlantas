package com.dflch.water.caFoto.data

import com.dflch.water.caFoto.data.model.FotoColaborador
import com.dflch.water.caFoto.data.network.FotoService
import com.dflch.water.caFoto.ui.model.FotoModel
import com.dflch.water.caFoto.ui.model.toDomain
import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.data.network.ItemService
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FotoRepository @Inject constructor(
    private val api: FotoService
) {
    suspend fun getColaboradorFromApi(numID: String): List<FotoModel> {
        val response: List<FotoColaborador> = api.getColaborador(numID)
        return response.map { it.toDomain() }
    }
}
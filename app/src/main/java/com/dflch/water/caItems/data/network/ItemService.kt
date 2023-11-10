package com.dflch.water.caItems.data.network

import com.dflch.water.caItems.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemService @Inject constructor(private val api: ItemApiClient) {

    suspend fun getAllItems(): List<Item> {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getAllItems()
                response.items ?: emptyList()
            }
        }  catch (e: Exception) { emptyList() }
    }
}


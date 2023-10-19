package com.dflch.water.caItems.data.network

import com.dflch.water.caItems.data.model.ItemResponse
import com.dflch.water.utils.Constants.GET_PATH_ITEMS
import retrofit2.http.GET

interface ItemApiClient {

    @GET(GET_PATH_ITEMS)
    suspend fun getAllItems(): ItemResponse

}
package com.dflch.water.caItems.data.network

import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.data.model.ItemResponse
import com.dflch.water.caUsers.data.model.UserResponse
import com.dflch.water.utils.Constants
import com.dflch.water.utils.Constants.GET_PATH_ITEMS
import retrofit2.Response
import retrofit2.http.GET

interface ItemApiClient {

    @GET(GET_PATH_ITEMS)
    suspend fun getAllItems(): Response<List<Item>>

}
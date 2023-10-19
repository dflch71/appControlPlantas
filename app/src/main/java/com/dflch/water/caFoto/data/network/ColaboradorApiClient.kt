package com.dflch.water.caFoto.data.network

import com.dflch.water.caFoto.data.model.Foto
import com.dflch.water.utils.Constants.GET_PATH_FOTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ColaboradorApiClient {

    @GET("$GET_PATH_FOTO/{id}")
    suspend fun getColaborador(@Path("id") id: String): Foto

}
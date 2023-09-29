package com.dflch.water.caUsers.data.network


import com.dflch.water.caUsers.data.model.UserResponse
import com.dflch.water.utils.Constants.GET_PATH_TERCEROS_ARR
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {

    @GET(GET_PATH_TERCEROS_ARR)
    suspend fun getAllUsers(): Response<List<UserResponse>>

}
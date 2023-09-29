package com.dflch.water.utils.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Conectado,
        Desconectado,
        Debil,
        Perdida
    }
}
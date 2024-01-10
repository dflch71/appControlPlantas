package com.dflch.water.caTurnos.data.model

import com.google.gson.annotations.SerializedName

data class Turno(
    @SerializedName("tur_id") val tur_id: Int,
    @SerializedName("tur_inicia") val tur_inicia: String,
    @SerializedName("tur_finaliza") val tur_finaliza: String,
    @SerializedName("tur_nombre") val tur_nombre: String
)
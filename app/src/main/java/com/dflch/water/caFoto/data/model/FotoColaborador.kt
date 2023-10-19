package com.dflch.water.caFoto.data.model

import com.google.gson.annotations.SerializedName

data class FotoColaborador(
    @SerializedName("idOperario") val idOperario: String,
    @SerializedName("rutaFoto") val rutaFoto: String,
    @SerializedName("foto") val foto: String
)
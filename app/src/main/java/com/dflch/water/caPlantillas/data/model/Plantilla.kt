package com.dflch.water.caPlantillas.data.model

import com.google.gson.annotations.SerializedName

data class Plantilla(
    @SerializedName("pls_id") val pls_id: Int,
    @SerializedName("plt_id") val plt_id: Int,
    @SerializedName("smt_id") val smt_id: Int,
    @SerializedName("sitio") val sitio: String,
    @SerializedName("plantilla") val plantilla: String
)


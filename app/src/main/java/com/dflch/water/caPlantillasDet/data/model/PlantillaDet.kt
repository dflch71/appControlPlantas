package com.dflch.water.caPlantillasDet.data.model

import com.google.gson.annotations.SerializedName

data class PlantillaDet(
    @SerializedName("pld_id") val pld_id: Int,
    @SerializedName("plt_id") val plt_id: Int,
    @SerializedName("lug_id") val lug_id: Int,
    @SerializedName("lug_nombre") val lug_nombre: String,
    @SerializedName("pld_orden") val pld_orden: Int,
    @SerializedName("car_id") val car_id: Int,
    @SerializedName("car_nombre") val car_nombre: String,
    @SerializedName("car_expresado") val car_expresado: String,
    @SerializedName("car_unidad") val car_unidad: String,
    @SerializedName("car_vrMin") val car_vrMin: Double,
    @SerializedName("car_vrMax") val car_vrMax: Double
)


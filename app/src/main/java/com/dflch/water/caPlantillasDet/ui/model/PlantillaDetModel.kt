package com.dflch.water.caPlantillasDet.ui.model

import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.data.model.PlantillaDet


data class PlantillaDetModel(
    val pld_id: Int,
    val plt_id: Int,
    val lug_id: Int,
    val lug_nombre: String,
    val pld_orden: Int,
    val car_id: Int,
    val car_nombre: String,
    val car_expresado: String,
    val car_unidad: String,
    val car_vrMin: Double,
    val car_vrMax: Double
)

fun PlantillaDet.toDomain() = PlantillaDetModel(
    pld_id = pld_id,
    plt_id = plt_id,
    lug_id = lug_id,
    lug_nombre = lug_nombre,
    pld_orden = pld_orden,
    car_id = car_id,
    car_nombre = car_nombre,
    car_expresado = car_expresado,
    car_unidad = car_unidad,
    car_vrMin = car_vrMin,
    car_vrMax = car_vrMax
)

fun PlantillaDetEntity.toDomain() = PlantillaDetModel(
    pld_id = pld_id,
    plt_id = plt_id,
    lug_id = lug_id,
    lug_nombre = lug_nombre,
    pld_orden = pld_orden,
    car_id = car_id,
    car_nombre = car_nombre,
    car_expresado = car_expresado,
    car_unidad = car_unidad,
    car_vrMin = car_vrMin.toDouble(),
    car_vrMax = car_vrMax.toDouble()
)

/*
 var id: Long = 0,
    var plt_id: Int,
    var pld_id: Int,
    var lug_id: Int,
    var lug_nombre: String,
    var pld_orden: Int,
    var car_id: Int,
    var car_nombre: String,
    var car_expresado: String,
    var car_unidad: String,
    var car_vrMin: Float,
    var car_vrMax: Float,
    var car_lectura: Float,
    var car_exportado: Boolean,
    var smt_id: Int,
    var ter_id: Int,
    var tur_id: Int,
    var ltc_altitud: Float,
    var ltc_latitud: Float,
    var ltc_longitud: Float,
    var ltc_fecha_hora: String
 */
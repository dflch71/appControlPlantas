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


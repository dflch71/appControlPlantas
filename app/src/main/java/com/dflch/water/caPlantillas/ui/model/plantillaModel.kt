package com.dflch.water.caPlantillas.ui.model

import com.dflch.water.caPlantillas.data.database.entities.PlantillaEntity
import com.dflch.water.caPlantillas.data.model.Plantilla

data class PlantillaModel(
    val pls_id: Int,
    val plt_id: Int,
    var smt_id: Int,
    val sitio: String,
    val plantilla: String
)

fun Plantilla.toDomain() = PlantillaModel(
    pls_id = pls_id,
    plt_id = plt_id,
    smt_id = smt_id,
    sitio = sitio,
    plantilla = plantilla
)

fun PlantillaEntity.toDomain() = PlantillaModel(
    pls_id = pls_id,
    plt_id = plt_id,
    smt_id = smt_id,
    sitio = sitio,
    plantilla = plantilla
)
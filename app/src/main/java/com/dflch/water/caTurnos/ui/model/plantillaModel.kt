package com.dflch.water.caTurnos.ui.model

import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import com.dflch.water.caTurnos.data.model.Turno
import com.google.gson.annotations.SerializedName

data class TurnoModel(
    val tur_id: Int,
    val tur_inicia: String,
    val tur_finaliza: String,
    val tur_nombre: String
)

fun Turno.toDomain() = TurnoModel(
    tur_id = tur_id,
    tur_inicia = tur_inicia,
    tur_finaliza = tur_finaliza,
    tur_nombre = tur_nombre
)

fun TurnoEntity.toDomain() = TurnoModel(
    tur_id = tur_id,
    tur_inicia = tur_inicia,
    tur_finaliza = tur_finaliza,
    tur_nombre = tur_nombre
)
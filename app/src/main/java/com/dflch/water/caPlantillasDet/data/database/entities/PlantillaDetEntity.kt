package com.dflch.water.caPlantillasDet.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PlantillaDetEntity", indices = arrayOf(Index(value = ["plt_id","pld_id","pld_orden"], unique = true)))
class PlantillaDetEntity (
    @PrimaryKey(autoGenerate = true)
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
)
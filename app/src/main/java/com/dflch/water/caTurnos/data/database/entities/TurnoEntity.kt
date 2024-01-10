package com.dflch.water.caTurnos.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "TurnoEntity", indices = arrayOf(Index(value = ["tur_id"], unique = true)))
data class TurnoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var tur_id: Int,
    var tur_inicia: String,
    var tur_finaliza: String,
    var tur_nombre: String
)
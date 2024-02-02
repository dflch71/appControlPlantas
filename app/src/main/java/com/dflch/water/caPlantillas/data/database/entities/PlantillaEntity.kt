package com.dflch.water.caPlantillas.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PlantillaEntity", indices = arrayOf(Index(value = ["plt_id","smt_id"], unique = true)))
data class PlantillaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var pls_id: Int,
    var plt_id: Int,
    var smt_id: Int,
    var plantilla: String,
    var sitio: String
)
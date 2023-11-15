package com.dflch.water.caItems.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dflch.water.caItems.ui.model.ItemModel

@Entity(tableName = "ItemEntity")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var itemId: Int,
    var itemCodi: Int,
    var itemCosa: String,
    var itemUnMe: String,
    var itemDesc: String,
    var itemUn: String,
    var itemValor: Double,
    var itemEmpresa: String,
    var itemCantidad: Double,
    var itemValorTotal: Double
)

fun ItemModel.toDatabase() =
    ItemEntity(
        id = id,
        itemId = itemId,
        itemCodi = itemCodi,
        itemCosa = itemCosa,
        itemUnMe = itemUnMe,
        itemDesc = itemDesc,
        itemUn = itemUn,
        itemValor = itemValor,
        itemEmpresa = itemEmpresa,
        itemCantidad = itemCantidad,
        itemValorTotal = itemValorTotal
    )

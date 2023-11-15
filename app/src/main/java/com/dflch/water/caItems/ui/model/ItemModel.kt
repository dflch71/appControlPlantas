package com.dflch.water.caItems.ui.model

import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item

data class ItemModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val itemId: Int,
    val itemCodi: Int,
    val itemCosa: String,
    val itemUnMe: String,
    val itemDesc: String,
    val itemUn: String,
    val itemValor: Double,
    val itemEmpresa: String,
    val itemCantidad: Double,
    val itemValorTotal: Double
)

fun Item.toDomain() = ItemModel(
    id = 0,
    itemId = itemId,
    itemCodi = itemCodi,
    itemCosa = itemCosa,
    itemUnMe = itemUnMe,
    itemDesc = itemDesc,
    itemUn = itemUn,
    itemValor = itemValor,
    itemEmpresa = itemEmpresa,
    itemCantidad = 0.0,
    itemValorTotal = 0.0
)

fun ItemEntity.toDomain() = ItemModel(
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

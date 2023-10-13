package com.dflch.water.caItems.data.model

import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("itemCodi") val itemCodi: Int,
    @SerializedName("itemCosa") val itemCosa: String,
    @SerializedName("itemUnMe") val itemUnMe: String,
    @SerializedName("itemDesc") val itemDesc: String,
    @SerializedName("itemUn") val itemUn: String,
    @SerializedName("itemValor") val itemValor: Double,
    @SerializedName("itemEmpresa") val itemEmpresa: String
)

fun Item.toItemEntity() = ItemEntity(
    id = 0,
    itemId = itemId,
    itemCodi = itemCodi,
    itemCosa = itemCosa,
    itemUnMe = itemUnMe,
    itemDesc = itemDesc,
    itemUn = itemUn,
    itemValor = itemValor,
    itemEmpresa = itemEmpresa
)
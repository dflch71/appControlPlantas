package com.dflch.water.caItems.data.model

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
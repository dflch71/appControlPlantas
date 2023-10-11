package com.dflch.water.caItems.data.model

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    val items: List<Item>,
    val status: String
)
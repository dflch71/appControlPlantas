package com.dflch.water.caUsers.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    //@SerializedName("id") val id: Int,
    @SerializedName("ter_id") val ter_id: Int,
    @SerializedName("ter_num_id") val ter_num_id: Int,
    @SerializedName("ter_nombre") val ter_nombre: String,
    @SerializedName("ter_apellido") val ter_apellido: String,
    @SerializedName("ter_clave") val ter_clave: String,
    @SerializedName("ter_activo") val ter_activo: Boolean,
    @SerializedName("ter_tecnica_plantas") val ter_tecnica_plantas: Boolean,
    @SerializedName("ter_tecnica_sql") val ter_tecnica_sql: Boolean,
    @SerializedName("ter_tecnica_valvulas") val ter_tecnica_valvulas: Boolean
)
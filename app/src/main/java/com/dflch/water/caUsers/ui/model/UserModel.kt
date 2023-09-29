package com.dflch.water.caUsers.ui.model

import com.dflch.water.caUsers.data.database.entities.UserEntity
import com.dflch.water.caUsers.data.model.UserResponse

data class UserModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val ter_id: Int,
    val ter_num_id: Int,
    val ter_nombre: String,
    val ter_apellido: String,
    val ter_clave: String,
    val ter_activo: Boolean,
    val ter_tecnica_plantas: Boolean,
    val ter_tecnica_sql: Boolean,
    val ter_tecnica_valvulas: Boolean
)

fun UserResponse.toDomain() = UserModel(id, ter_id, ter_num_id, ter_nombre, ter_apellido, ter_clave, ter_activo, ter_tecnica_plantas, ter_tecnica_sql, ter_tecnica_valvulas)
fun UserEntity.toDomain() = UserModel(id, ter_id, ter_num_id, ter_nombre, ter_apellido, ter_clave, ter_activo, ter_tecnica_plantas, ter_tecnica_sql, ter_tecnica_valvulas)







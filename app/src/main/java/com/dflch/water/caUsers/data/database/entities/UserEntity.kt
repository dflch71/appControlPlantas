package com.dflch.water.caUsers.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dflch.water.caUsers.ui.model.UserModel

@Entity(
    tableName = "UserEntity"
    //, indices = [Index(value = ["ter_num_id"], unique = true)]
)
data class UserEntity(
             @PrimaryKey(autoGenerate = true)
             var id: Int,
             var ter_id: Int,
             var ter_num_id: Int,
             var ter_nombre: String,
             var ter_apellido: String,
             var ter_clave: String,
             var ter_activo: Boolean,
             var ter_tecnica_plantas: Boolean,
             var ter_tecnica_sql: Boolean,
             var ter_tecnica_valvulas: Boolean
)

fun UserModel.toDatabase() =
    UserEntity(
        id = id,
        ter_id = ter_id,
        ter_num_id = ter_num_id,
        ter_nombre = ter_nombre,
        ter_apellido = ter_apellido,
        ter_clave = ter_clave,
        ter_activo = ter_activo,
        ter_tecnica_plantas = ter_tecnica_plantas,
        ter_tecnica_sql = ter_tecnica_sql,
        ter_tecnica_valvulas = ter_tecnica_valvulas
    )


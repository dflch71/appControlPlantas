package com.dflch.water.caFoto.ui.model

import com.dflch.water.caFoto.data.model.FotoColaborador
import com.dflch.water.caUsers.data.model.UserResponse
import com.dflch.water.caUsers.ui.model.UserModel

data class FotoModel(
    val idOperario: String,
    val rutaFoto: String,
    val foto: String
)

fun FotoColaborador.toDomain() = FotoModel(
    idOperario,
    rutaFoto,
    foto
)

package com.dflch.water.caPlantillasDet.ui.model

import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.data.model.LugaresMuestra
import com.dflch.water.caPlantillasDet.data.model.PlantillaDet


data class LugaresMuestraModel(
    val lug_nombre: String,
    val plt_id: Int
)
fun LugaresMuestra.toDomain() = LugaresMuestraModel(
    lug_nombre = lug_nombre,
    plt_id = plt_id
)






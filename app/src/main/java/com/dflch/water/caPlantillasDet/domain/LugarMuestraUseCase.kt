package com.dflch.water.caPlantillasDet.domain

import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import com.dflch.water.caPlantillasDet.data.model.LugaresMuestra
import com.dflch.water.caPlantillasDet.ui.model.LugaresMuestraModel
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caUsers.ui.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LugarMuestraUseCase @Inject constructor(private val plantillaDetRepository: PlantillaDetRepository) {

    operator fun invoke(): Flow<List<LugaresMuestraModel>> = plantillaDetRepository.lugaresMuestra

}



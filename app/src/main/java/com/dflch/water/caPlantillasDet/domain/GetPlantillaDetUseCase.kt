package com.dflch.water.caPlantillasDet.domain

import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantillaDetUseCase @Inject constructor(private val plantillaDetRepository: PlantillaDetRepository) {
    operator fun invoke(): Flow<List<PlantillaDetModel>> = plantillaDetRepository.plantillas
}
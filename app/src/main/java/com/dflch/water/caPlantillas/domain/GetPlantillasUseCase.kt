package com.dflch.water.caPlantillas.domain

import com.dflch.water.Plantillas.data.PlantillaRepository
import com.dflch.water.caPlantillas.ui.model.PlantillaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantillasUseCase @Inject constructor(private val plantillaRepository: PlantillaRepository) {
    operator fun invoke(): Flow<List<PlantillaModel>> = plantillaRepository.plantillas
}
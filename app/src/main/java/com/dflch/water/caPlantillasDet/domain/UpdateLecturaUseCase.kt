package com.dflch.water.caPlantillasDet.domain

import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import javax.inject.Inject

class UpdateLecturaUseCase @Inject constructor(private val plantillaDetRepository: PlantillaDetRepository) {

    suspend operator fun invoke(plantillaDetModel: PlantillaDetModel) {
        plantillaDetRepository.updateLecturaDet(plantillaDetModel)
    }

}


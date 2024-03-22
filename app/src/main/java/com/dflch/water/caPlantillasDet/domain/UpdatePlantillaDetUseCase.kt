package com.dflch.water.caPlantillasDet.domain

import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import javax.inject.Inject


class UpdatePlantillaDetUseCase @Inject constructor (private val repository: PlantillaDetRepository) {
    suspend operator fun invoke(plantillaDetModel: PlantillaDetModel) {
        repository.updatePDL(plantillaDetModel)
    }

}


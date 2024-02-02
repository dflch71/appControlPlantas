package com.dflch.water.caPlantillasDet.domain

import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import javax.inject.Inject

class MergePlantillaDetUseCase @Inject constructor(private val plantillaDetRepository: PlantillaDetRepository){

    suspend operator fun invoke() { plantillaDetRepository.requestPlantillasDet() }

}
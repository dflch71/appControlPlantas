package com.dflch.water.caPlantillas.domain


import com.dflch.water.Plantillas.data.PlantillaRepository
import javax.inject.Inject

class MergePlantillaUseCase @Inject constructor(private val plantillaRepository: PlantillaRepository){

    suspend operator fun invoke() { plantillaRepository.requestPlantillas() }

}
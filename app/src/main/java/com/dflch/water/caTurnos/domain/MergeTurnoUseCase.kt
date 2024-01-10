package com.dflch.water.caTurnos.domain

import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caTurnos.data.TurnoRepository
import javax.inject.Inject

class MergeTurnoUseCase @Inject constructor(private val turnoRepository: TurnoRepository){

    suspend operator fun invoke() { turnoRepository.requestTurnos() }

}
package com.dflch.water.caTurnos.domain

import com.dflch.water.caTurnos.data.TurnoRepository
import com.dflch.water.caTurnos.ui.model.TurnoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TurnoActivoUseCase @Inject constructor(private val turnoRepository: TurnoRepository) {

    suspend operator fun invoke(): List<TurnoModel> {
        return withContext(Dispatchers.IO) {
            turnoRepository.getTurnoActivo()
        }
    }

}


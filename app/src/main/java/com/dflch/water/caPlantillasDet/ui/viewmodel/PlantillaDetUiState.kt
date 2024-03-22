package com.dflch.water.caPlantillasDet.ui.viewmodel

import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel

sealed interface PlantillaDetUiState {

    object Loading : PlantillaDetUiState
    data class Error(val throwable: Throwable) : PlantillaDetUiState
    data class Success(val plantillasDet: List<PlantillaDetModel>) : PlantillaDetUiState

}
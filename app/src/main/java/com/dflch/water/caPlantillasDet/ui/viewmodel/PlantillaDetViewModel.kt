package com.dflch.water.caPlantillasDet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caPlantillasDet.domain.MergePlantillaDetUseCase
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantillaDetViewModel @Inject constructor(
    private val mergePlantillaDetUseCase: MergePlantillaDetUseCase
): ViewModel() {

    private val _statePlantillaDet = MutableStateFlow(UiStatePlantillaDet())
    val statePlantillaDet: StateFlow<UiStatePlantillaDet> = _statePlantillaDet

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getPlantillasDetCloud()
    }

    private fun getPlantillasDetCloud(){
        //En la interfaz de SplashScreen consultar los valores de items del WS
        //y grabarlos a la Base de datos

        viewModelScope.launch {

            _statePlantillaDet.value = UiStatePlantillaDet(
                loading = true,
                status = "Cargando Plantillas...",
                items = emptyList()
            )
            mergePlantillaDetUseCase()
        }
    }

    data class UiStatePlantillaDet(
        val loading: Boolean = false,
        val status: String = "Success",
        val items: List<PlantillaDetModel> = emptyList()
    )

    data class UiState(
        val loading: Boolean = false,
        val status: String = "EM",
        val listItems: List<PlantillaDetModel> = emptyList()
    )

}
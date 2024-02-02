package com.dflch.water.caTurnos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caTurnos.domain.MergeTurnoUseCase
import com.dflch.water.caTurnos.ui.model.TurnoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurnoViewModel @Inject constructor(
    private val mergeTurnoUseCase: MergeTurnoUseCase,
): ViewModel() {

    private val _stateTurno = MutableStateFlow(UiStateTurno())
    val stateTurno: StateFlow<UiStateTurno> = _stateTurno

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getTurnosCloud()
    }

    private fun getTurnosCloud(){
        //En la interfaz de SplashScreen consultar los valores de items del WS
        //y grabarlos a la Base de datos

        viewModelScope.launch {

            _stateTurno.value = UiStateTurno(
                loading = true,
                status = "Cargando Turnos...",
                items = emptyList()
            )
            mergeTurnoUseCase()
        }
    }


    data class UiStateTurno(
        val loading: Boolean = false,
        val status: String = "Success",
        val items: List<TurnoModel> = emptyList()
    )

    data class UiState(
        val loading: Boolean = false,
        val status: String = "EM",
        val listItems: List<TurnoModel> = emptyList()
    )

}
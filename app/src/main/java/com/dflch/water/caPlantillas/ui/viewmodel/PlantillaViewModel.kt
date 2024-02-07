package com.dflch.water.caPlantillas.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dflch.water.caPlantillas.domain.GetPlantillasUseCase
import com.dflch.water.caPlantillas.domain.MergePlantillaUseCase
import com.dflch.water.caPlantillas.ui.model.PlantillaModel
import com.dflch.water.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantillaViewModel @Inject constructor(
    private val mergePlantillaUseCase: MergePlantillaUseCase,
    private val getPlantillasUseCase: GetPlantillasUseCase
): ViewModel() {

    private val _statePlantilla = MutableStateFlow(UiStatePlantilla())
    val statePlantilla: StateFlow<UiStatePlantilla> = _statePlantilla

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private val _namePlantilla = MutableLiveData<String>()
    val namePlantilla: LiveData<String> = _namePlantilla

    private val _nameLugar = MutableLiveData<String>()
    val nameLugar: LiveData<String> = _nameLugar

    init {
        getPlantillasCloud()
        getPlantillasDB()
    }

    private fun getPlantillasCloud(){
        //En la interfaz de SplashScreen consultar los valores de items del WS
        //y grabarlos a la Base de datos

        viewModelScope.launch {

            _statePlantilla.value = UiStatePlantilla(
                loading = true,
                status = "Cargando Plantillas...",
                plantillas = emptyList()
            )
            mergePlantillaUseCase()
        }
    }

    private fun getPlantillasDB(){

        //Consular los items de la Base de datos
        viewModelScope.launch {

            _state.value = UiState(
                loading = true,
                status = "EM",
                listPlantillas = emptyList()
            )

            val repository = getPlantillasUseCase()
            repository.collect {
                _state.value = UiState(listPlantillas = it)
            }

        }
    }


    fun onItemSelectec(navController: NavController, itemId: Int, plantilla: String,  lugar : String, ctx: Context) {

        try {
            //navController.navigate(AppScreens.ItemDetailScreen.route + "/${itemId}")
            navController.navigate(route = AppScreens.PlantillaDetScreen.route)

            _namePlantilla.value = plantilla
            _nameLugar.value = lugar

        } catch (e: Exception) {
            Toast
                .makeText(ctx, "Error: ${e.message} $itemId", Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun Context.showToast(message: String,length: Int = Toast.LENGTH_LONG){
        Toast.makeText(this, message, length).show()
    }

    data class UiStatePlantilla(
        val loading: Boolean = false,
        val status: String = "Success",
        val plantillas: List<PlantillaModel> = emptyList()
    )

    data class UiState(
        val loading: Boolean = false,
        val status: String = "EM",
        val listPlantillas: List<PlantillaModel> = emptyList()
    )

}
package com.dflch.water.caPlantillasDet.ui.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dflch.water.PlantillasDet.data.PlantillaDetRepository
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caPlantillasDet.domain.GetPlantillaDetUseCase
import com.dflch.water.caPlantillasDet.domain.LugarMuestraUseCase
import com.dflch.water.caPlantillasDet.domain.MergePlantillaDetUseCase
import com.dflch.water.caPlantillasDet.domain.UpdateLecturaUseCase
import com.dflch.water.caPlantillasDet.domain.UpdatePlantillaDetUseCase
import com.dflch.water.caPlantillasDet.ui.model.LugaresMuestraModel
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetUiState.Success
import com.dflch.water.navigation.AppScreens
import com.dflch.water.utils.Constants
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantillaDetViewModel @Inject constructor(
    private val mergePlantillaDetUseCase: MergePlantillaDetUseCase,
    private val lugarMuestraUseCase: LugarMuestraUseCase,
    private val getPlantillaDetUseCase: GetPlantillaDetUseCase,
    private val updateLecturaUseCase: UpdateLecturaUseCase,
    private val updatePlantillaDetUseCase: UpdatePlantillaDetUseCase,
    private val plantillaDetRepository: PlantillaDetRepository

): ViewModel() {

    val allItems: LiveData<List<PlantillaDetEntity>> = plantillaDetRepository.allItems.asLiveData()

    //Consumir Caso de uso
    val uiState: StateFlow<PlantillaDetUiState> = getPlantillaDetUseCase().map( ::Success )
        .catch { Error(it) }
        .stateIn( viewModelScope, SharingStarted.WhileSubscribed(5000), PlantillaDetUiState.Loading )

    private val _statePlantillaDet = MutableStateFlow(UiStatePlantillaDet())
    val statePlantillaDet: StateFlow<UiStatePlantillaDet> = _statePlantillaDet

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private val _lugares = MutableStateFlow(UiStateLugarMuestra())
    val lugares: StateFlow<UiStateLugarMuestra> = _lugares

    //Almacenar variables de PlantillaDetModel
    private val _pldID = MutableLiveData<Int>()
    val pldID: LiveData<Int> = _pldID

    private val _lug_nombre = MutableLiveData<String>()
    val lug_nombre: LiveData<String> = _lug_nombre

    private val _car_nombre = MutableLiveData<String>()
    val car_nombre: LiveData<String> = _car_nombre

    private val _car_expresado = MutableLiveData<String>()
    val car_expresado: LiveData<String> = _car_expresado

    private val _car_unidad = MutableLiveData<String>()
    val car_unidad: LiveData<String> = _car_unidad

    private val _car_vrMin = MutableLiveData<Float>()
    val car_vrMin: LiveData<Float> = _car_vrMin

    private val _car_vrMax = MutableLiveData<Float>()
    val car_vrMax: LiveData<Float> = _car_vrMax

    private val _car_lectura = MutableLiveData<Float>()
    val car_lectura: LiveData<Float> = _car_lectura

    private val _car_exportado = MutableLiveData<Boolean>()
    val car_exportado: LiveData<Boolean> = _car_exportado

    private val _ltc_fecha_hora = MutableLiveData<String>()
    val ltc_fecha_hora: LiveData<String> = _ltc_fecha_hora

    init {
        getPlantillasDetCloud()
        getPlantillasDetDB()
        getLugaresMuestra()
    }

    fun update(plantillaDet: PlantillaDetModel) {
        viewModelScope.launch {
            updatePlantillaDetUseCase(plantillaDet)
        }
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

    fun getPlantillasDetDB(){
        viewModelScope.launch {
            _statePlantillaDet.value = UiStatePlantillaDet(
                loading = true,
                status = "Cargando Plantillas...",
                items = emptyList()
            )

            val repository = getPlantillaDetUseCase()
            repository.collect {
                _state.value = UiState(listItems = it)
            }

        }
    }

    fun getLugaresMuestra() {
        viewModelScope.launch {
            _lugares.value = UiStateLugarMuestra(
                loading = true,
                status = "Cargando Lugares de Muestra...",
                listLugares = emptyList()
            )

            lugarMuestraUseCase().collect {
                _lugares.value = UiStateLugarMuestra(
                    loading = false,
                    status = "OK",
                    listLugares = it
                )
            }
        }

    }

    fun onItemSelectec(navController: NavController, pltID: Int,  lug_Nombre: String, car_Nombre: String, car_Expresado: String, car_Unidad: String,
                       car_VrMin: Float, car_VrMax: Float, car_Lectura: Float, car_Exportado: Boolean, ltc_Fecha_Hora: String) {

        _pldID.value = pltID
        _lug_nombre.value = lug_Nombre
        _car_nombre.value = car_Nombre
        _car_expresado.value = car_Expresado
        _car_unidad.value = car_Unidad
        _car_vrMin.value = car_VrMin
        _car_vrMax.value = car_VrMax
        _car_lectura.value = car_Lectura
        _car_exportado.value = car_Exportado
        _ltc_fecha_hora.value = ltc_Fecha_Hora

        try {
            navController.navigate(AppScreens.MuestraScreen.route)
        } catch (_: Exception) { }

    }

    fun validarLectura(lectura: Float): Boolean {

        val vrMin = _car_vrMin.value!!
        val vrMax = _car_vrMax.value!!

        _car_lectura.value = lectura

        return if ((vrMin == 0.0f) && (vrMax == 0.0f)) { false }
        else { !((lectura >= vrMin) && (lectura <= vrMax)) }

        /*
        if (lectura < car_vrMin || lectura > car_vrMax) {
            Toast.makeText(
                currentContext,
                "EL VALOR DE LA MUESTRA DEBE ESTAR ENTRE $car_vrMin y $car_vrMax",
                Toast.LENGTH_LONG
            ).show()
        } else {
            navController.popBackStack()
        }
        */
    }

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateLectura(plantillaDetModel: PlantillaDetModel, newValue: Double) {
        try {

            _car_lectura.value = newValue.toFloat()
            _ltc_fecha_hora.value = Constants.currentDateTime().toString()
            val updateItem = plantillaDetModel.copy(
                ltc_fecha_hora = _ltc_fecha_hora.value!!,
                car_lectura = _car_lectura.value!!.toDouble()
            )

            //Actualizar los items de la Base de datos
            //viewModelScope.launch {
            //    updatePlantillaDetUseCase(updateItem)
            //}

            GlobalScope.launch {
                updatePlantillaDetUseCase(updateItem)
            }


        } catch (e: Exception) {
            Log.e("Error", e.toString())
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

    data class UiStateLugarMuestra(
        val loading: Boolean = false,
        val status: String = "OK",
        val listLugares: List<LugaresMuestraModel> = emptyList()
    )

}
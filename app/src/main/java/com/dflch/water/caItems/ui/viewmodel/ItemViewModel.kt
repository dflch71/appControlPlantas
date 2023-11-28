package com.dflch.water.caItems.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dflch.water.caItems.domain.GetItemsAPIUseCase
import com.dflch.water.caItems.domain.GetItemsActoUseCase
import com.dflch.water.caItems.domain.GetItemsAlcantUseCase
import com.dflch.water.caItems.domain.GetItemsUseCase
import com.dflch.water.caItems.domain.MergeItemUseCase
import com.dflch.water.caItems.domain.UpdateItemUseCase
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.screens.drawer.items.ItemDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItemsAPIUseCase: GetItemsAPIUseCase,
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemsActoUseCase: GetItemsActoUseCase,
    private val getItemsAlcantUseCase: GetItemsAlcantUseCase,
    private val mergeItemUseCase: MergeItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
): ViewModel() {

    private val _stateItem = MutableStateFlow(UiStateItem())
    val stateItem: StateFlow<UiStateItem> = _stateItem

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getItemsCloud()
        //getItemsActoDB()
        getItemsAlcantDB()
    }

    private fun getItemsCloud(){
        //En la interfaz de SplashScreen consultar los valores de items del WS
        //y grabarlos a la Base de datos

        viewModelScope.launch {

            _stateItem.value = UiStateItem(
                loading = true,
                status = "Cargando Items...",
                items = emptyList()
            )
            mergeItemUseCase()
        }
    }

    fun getItemsDB(){

        //Consular los items de la Base de datos
        viewModelScope.launch {

            _state.value = UiState(
                loading = true,
                status = "EM",
                listItems = emptyList()
            )

            val repository = getItemsUseCase()
            repository.collect {
                _state.value = UiState(listItems = it)
            }

        }
    }

    fun getItemsActoDB(){

        //Consular los items de la Base de datos
        viewModelScope.launch {

            _state.value = UiState(
                loading = true,
                status = "AC",
                listItems = emptyList()
            )

            val repository = getItemsActoUseCase()
            repository.collect {
                _state.value = UiState(listItems = it)
            }

        }
    }

    fun getItemsAlcantDB(){

        //Consular los items de la Base de datos
        viewModelScope.launch {

            _state.value = UiState(
                loading = true,
                status = "AL",
                listItems = emptyList()
            )

            val repository = getItemsAlcantUseCase()
            repository.collect {
                _state.value = UiState(listItems = it)
            }

        }
    }

    fun updItemsDB(){

        //Consular los items de la Base de datos
        viewModelScope.launch {

            _state.value = UiState(
                loading = true,
                status = "EM",
                listItems = emptyList()
            )

            updateItemUseCase()

        }
    }

    /*private fun getAllItemCloud() {
        //Estos metodos con los try catch no funcionan

        viewModelScope.launch {
            try {
                _stateItem.value = UiStateItem(
                    loading = true,
                    status = "Cargando Items...",
                    items = emptyList()
                )
                getItemsAPIUseCase()

            } catch (e: Exception) {
                _stateItem.value = UiStateItem(status = "Exception: ${e.message}", loading = false)
            } catch (e: HttpException) {
                _stateItem.value = UiStateItem(status = "HttpException: ${e.message}", loading = false)
            } catch (e: IOException) {
                _stateItem.value = UiStateItem(status = "IOException: ${e.message}", loading = false)
            }

        }
    }

    fun getAllItemDB(){

        viewModelScope.launch {
            try {
               _stateItem.value = UiStateItem(
                    loading = true,
                    status = "Cargando Items DB...",
                    items = emptyList()
                )

                val repository = getItemsUseCase()
                repository.collect {
                    _stateItem.value = UiStateItem(items = it)
                }

            } catch (e: Exception){
                _stateItem.value = UiStateItem(status = "Exception: ${e.message}")
            }
        }


    }*/

    fun onItemSelectec(navController: NavController, itemId: Int,  msj: String, ctx: Context) {

        Toast
            .makeText(ctx, "$itemId $msj", Toast.LENGTH_SHORT)
            .show()

        try {
            navController.navigate(AppScreens.ItemDetailScreen.route + "/$itemId")
        } catch (e: Exception) {
            Toast
                .makeText(ctx, "Error: ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }

    }

    data class UiStateItem(
        val loading: Boolean = false,
        val status: String = "Success",
        val items: List<ItemModel> = emptyList()
    )


    data class UiState(
        val loading: Boolean = false,
        val status: String = "EM",
        val listItems: List<ItemModel> = emptyList()
    )

}
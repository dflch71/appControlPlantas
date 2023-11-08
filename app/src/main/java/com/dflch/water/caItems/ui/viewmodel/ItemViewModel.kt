package com.dflch.water.caItems.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caItems.domain.GetItemsAPIUseCase
import com.dflch.water.caItems.domain.GetItemsUseCase
import com.dflch.water.caItems.domain.MergeItemUseCase
import com.dflch.water.caItems.ui.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItemsAPIUseCase: GetItemsAPIUseCase,
    private val getItemsUseCase: GetItemsUseCase,
    private val mergeItemUseCase: MergeItemUseCase
): ViewModel() {

    private val _stateItem = MutableStateFlow(UiStateItem())
    val stateItem: StateFlow<UiStateItem> = _stateItem

    init {
        getItemsCloud()
    }


    private fun getItemsCloud(){

        viewModelScope.launch {

            _stateItem.value = UiStateItem(
                loading = true,
                status = "Cargando Items...",
                items = emptyList()
            )

            mergeItemUseCase()
            val repository = getItemsUseCase()
            repository.collect {
                _stateItem.value = UiStateItem(items = it)
            }

        }
    }

    private fun getAllItemCloud() {
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
    }

    data class UiStateItem(
        val loading: Boolean = false,
        val status: String = "Success",
        val items: List<ItemModel> = emptyList()
    )

}
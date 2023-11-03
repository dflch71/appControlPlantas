package com.dflch.water.caItems.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caItems.domain.GetItemsAPIUseCase
import com.dflch.water.caItems.domain.GetItemsUseCase
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caUsers.ui.UserUiState.Success
import com.dflch.water.caUsers.ui.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.http.GET
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItemsAPIUseCase: GetItemsAPIUseCase,
    private val getItemsUseCase: GetItemsUseCase
): ViewModel() {

    private val _stateItem = MutableStateFlow(UiStateItem())
    val stateItem: StateFlow<UiStateItem> = _stateItem

    init {
        viewModelScope.launch {
            _stateItem.value = UiStateItem(loading = true)

            try {
                getItemsAPIUseCase()
            } catch (e: Exception) {
                _stateItem.value = UiStateItem( status = "Exception: ${e.message}")
            } catch (e: HttpException) {
                _stateItem.value = UiStateItem( status = "HttpException: ${e.message}")
            } catch (e: IOException) {
                _stateItem.value = UiStateItem( status = "IOException: ${e.message}")
            }
        }

        GetAllItem()
    }

    fun GetAllItem(){
        try {
            viewModelScope.launch {
                val repository = getItemsUseCase()
                repository.collect {
                    _stateItem.value = UiStateItem(items = it)
                }
            }
        } catch (e: Exception){
            _stateItem.value = UiStateItem(status = "Exception: ${e.message}")
        }
    }

    data class UiStateItem(
        val loading: Boolean = false,
        val status: String = "Success",
        val items: List<ItemModel> = emptyList()
    )

}
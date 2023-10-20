package com.dflch.water.caFoto.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflch.water.caFoto.domain.FotoUseCase
import com.dflch.water.caFoto.ui.model.FotoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class FotoViewModel @Inject constructor(fotoUseCase: FotoUseCase): ViewModel() {

    private val _stateFoto = MutableStateFlow(UiStateFoto())
    val stateFoto: StateFlow<UiStateFoto> = _stateFoto

    private val _idUserFoto = MutableLiveData<String>()
    val idUserFoto: LiveData<String> = _idUserFoto

    private val _base64 = MutableLiveData<String>()
    val base64: LiveData<String> = _base64

    private val _foto = mutableStateListOf<FotoModel>()
    private val foto: List<FotoModel> = _foto

    init {
        viewModelScope.launch {
            _stateFoto.value = UiStateFoto(loading = true)

            try {
                fotoUseCase.colaboradorID(_idUserFoto.value.toString())
            } catch (e: Exception) {
                _stateFoto.value = UiStateFoto(status = "Exception: ${e.message}")
            } catch (e: HttpException) {
                _stateFoto.value = UiStateFoto(status = "HttpException: ${e.message}")
            } catch (e: IOException) {
                _stateFoto.value = UiStateFoto(status = "IOException: ${e.message}")
            }
        }
    }


    data class UiStateFoto(
        val loading: Boolean = false,
        val status: String = "Success",
        val foto: List<FotoModel> = emptyList()
    )

}
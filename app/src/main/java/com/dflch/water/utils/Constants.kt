package com.dflch.water.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Constants {
    var IP      = "192.168.72.222"
    var IP_WIFI = "192.168.72.25"
    var IP_CEL  = "181.204.95.204"
    var PUERTO  = "8080"

    var MEDICIONES_URL = "http://$IP:$PUERTO/datasnap/rest/TServerMethods/"

    const val GET_PATH_TERCEROS = "terceros"
    const val GET_PATH_TERCEROS_ARR = "tercerosArr"
    const val GET_PATH_ITEMS = "items"
    const val GET_PATH_TURNOS = "turnos"
    const val GET_PATH_PLANTILLAS = "plantillas"
    const val GET_PATH_PLANTILLA_DET = "plantillaDet"
    const val GET_PATH_LECTURAS = "lecturas"
    const val GET_PATH_FOTO = "publicarcolaborador"
    const val GET_PATH_HISTORICO = "historicoLecturas"
    const val GET_PATH_MIN_MAX = "historicoMinMax"
    const val GET_PATH_OBSERVACIONES = "observaciones"

    //POST
    const val POST_SAVE_LECTURAS = "saveLecturas"
    const val POST_SAVE_OBSERVACIONES = "saveObservaciones"

    const val STATUS_PROPERTY = "status"
    const val TERCEROS_PROPERTY = "terceros"
    const val TURNOS_PROPERTY = "turnos"
    const val PLANTILLAS_PROPERTY = "plantillas"
    const val PLANTILLA_DET_PROPERTY = "plantillaDet"
    const val LECTURAS_PROPERTY = "lecturas"
    const val FOTO_PROPERTY = "fotoColaborador"
    const val HISTORICO_PROPERTY = "historico"
    const val HISTORICO_MIN_MAX_PROPERTY = "historicoMinMax"
    const val OBSERVACIONES_PROPERTY = "observaciones"

    const val SUCCESS = 1
    const val ERROR = 2

    const val SHOW = true
    const val HIDE = false

    //Valores Usuario
    var TER_NUM_ID: Int = -1
    var TER_NOMBRE: String = ""
    var TER_APELLIDO: String = ""
    var TER_CLAVE: String = ""
    var TER_ACTIVO: Boolean = true
    var TER_PLANTAS: Boolean = false
    var TER_CONSULTAS: Boolean = false
    var TER_VALVULAS: Boolean = false

    //Valores Lectura
    var LTC_ID: Int = -1
    var PLS_ID: Int = -1
    var PLT_ID: Int = -1
    var PLD_ID: Int = -1
    var CAR_ID: Int = -1
    var LUG_ID: Int = -1
    var PLD_ORDEN: Int = -1
    var SMT_ID: Int = -1
    var TER_ID: Int = -1
    var TUR_ID: Int = -1
    var LTC_ALTITUD: Float = 0.00f
    var LTC_LATITUD: Float = 0.00f
    var LTC_LONGITUD: Float = 0.00f
    var LTC_LECTURA: Float = 0.00f
    var LTC_RUTA_FOTO: String = ""
    var LTC_PROCESADO: Boolean = false
    var LTC_FECHA_HORA: String = ""

    //ValoresPlantilla
    var NOM_PLANTILLA: String = ""
    var NOM_SITIO: String = ""
    var NOM_TURNO: String = ""

    //Valores Característica
    var NOM_CARACTERISTICA: String = ""
    var NOM_LUGAR: String = ""
    var NOM_RANGO: String = ""
    var VMIN: Float = 0f
    var VMAX: Float = 0f

    //Tipo Historico a Consultar WS
    var TIPO_HISTORICO: Int = 2
    var TIPO_HISTORICO_LECT: Int = 2

    //PERMISOS MENU
    var PERMISO_PLANTAS: Boolean   = false
    var PERMISO_VACTOR: Boolean    = false
    var PERMISO_VEHICULOS: Boolean = false

    fun floatFormat(amount: String): String? {
        val formatter = DecimalFormat("###,###,###,##0")
        return formatter.format(amount.toDouble())
    }

    fun floatFormatDecimal(amount: String): String? {
        val formatter = DecimalFormat("###,###,###,##0.00")
        return formatter.format(amount.toDouble())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDateTime(): String? {
        val current = LocalDateTime.now()

        //val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM) //20 nov. 2020 8:14:35 a.m.
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)

        return formatted
    }

    fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (ex: NumberFormatException) {
            false
        }
    }


}


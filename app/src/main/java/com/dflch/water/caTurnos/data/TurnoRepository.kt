package com.dflch.water.caTurnos.data

import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.data.network.ItemService
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.model.toDomain
import com.dflch.water.caTurnos.data.database.dao.TurnoDao
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import com.dflch.water.caTurnos.data.model.Turno
import com.dflch.water.caTurnos.data.network.TurnoService
import com.dflch.water.caTurnos.ui.model.TurnoModel
import com.dflch.water.caTurnos.ui.model.toDomain
import com.dflch.water.caUsers.ui.model.UserModel
import com.dflch.water.caUsers.ui.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TurnoRepository @Inject constructor(
    private val api: TurnoService,
    private val turnoDao: TurnoDao
) {

    suspend fun getAllTurnosFromApi(): List<TurnoModel> {
        val response: List<Turno> = api.getAllTurnos()
        return response.map { it.toDomain() }
    }

    val turnos: Flow<List<TurnoModel>> =
        turnoDao.getAllTurnos().map {
                items -> items.map {
                    it.toDomain()
                }
        }


    suspend fun addTurno(turnoModel: TurnoModel) {
        turnoDao.addTurno(turnoModel.toData())
    }

    suspend fun updateTurno(turnoModel: TurnoModel) {
        turnoDao.updateTurno(turnoModel.toData())
    }

    suspend fun deleteTurno(turnoModel: TurnoModel) {
        turnoDao.deleteTurno(turnoModel.toData())
    }

    suspend fun deleteAllTurnos() {
        turnoDao.deleteAllTurno()
    }

    suspend fun insertAllTurnos(turnos: List<TurnoModel>) {
        turnoDao.insertAllTurnos(turnos.map { it.toData() })
    }

    suspend fun updatetAllTurnos(turnos: List<TurnoModel>) {
        for (t in turnos) {
            turnoDao.updateTurnoSQL(
                t.tur_id,
                t.tur_nombre,
                t.tur_inicia,
                t.tur_finaliza
            )
        }
    }

    suspend fun requestTurnos(){
        val isDBEmpty = turnoDao.countTurnos() == 0
        if (isDBEmpty){
            //Insertar los items
            insertAllTurnos(getAllTurnosFromApi())
        } else {
            updatetAllTurnos(getAllTurnosFromApi())
        }
    }

    fun getTurnoActivo() : List<TurnoModel> {

        return turnoDao.getTurnoActivo().map { it.toDomain() }

    }

    private fun TurnoModel.toData(): TurnoEntity {
        return TurnoEntity(
            0,
            this.tur_id,
            this.tur_inicia,
            this.tur_finaliza,
            this.tur_nombre
        )
    }

}
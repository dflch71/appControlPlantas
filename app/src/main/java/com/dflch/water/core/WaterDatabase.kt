package com.dflch.water.core

import androidx.room.Database
import androidx.room.RoomDatabase

import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caPlantillas.data.database.dao.PlantillaDao
import com.dflch.water.caPlantillas.data.database.entities.PlantillaEntity
import com.dflch.water.caPlantillasDet.data.database.dao.PlantillaDetDao
import com.dflch.water.caPlantillasDet.data.database.entities.PlantillaDetEntity
import com.dflch.water.caTurnos.data.database.dao.TurnoDao
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import com.dflch.water.caUsers.data.database.dao.UserDao
import com.dflch.water.caUsers.data.database.entities.UserEntity

@Database(entities = [  UserEntity::class,
                        ItemEntity::class,
                        TurnoEntity::class,
                        PlantillaEntity::class,
                        PlantillaDetEntity::class
                     ] ,
    version = 1, exportSchema = false)

abstract class WaterDatabase: RoomDatabase() {
    //DAO
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
    abstract fun turnoDao(): TurnoDao
    abstract fun plantillaDao(): PlantillaDao
    abstract fun plantillaDetDao(): PlantillaDetDao
}
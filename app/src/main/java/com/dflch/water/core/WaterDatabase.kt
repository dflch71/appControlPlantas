package com.dflch.water.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caTurnos.data.database.dao.TurnoDao
import com.dflch.water.caTurnos.data.database.entities.TurnoEntity
import com.dflch.water.caUsers.data.database.dao.UserDao
import com.dflch.water.caUsers.data.database.entities.UserEntity

@Database(entities = [
    UserEntity::class,
    ItemEntity::class,
    TurnoEntity::class],
    version = 1, exportSchema = false)

abstract class WaterDatabase: RoomDatabase() {
    //DAO
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
    abstract fun turnoDao(): TurnoDao
}
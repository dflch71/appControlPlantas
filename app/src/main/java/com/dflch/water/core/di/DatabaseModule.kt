package com.dflch.water.core.di

import android.content.Context
import androidx.room.Room
import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caPlantillas.data.database.dao.PlantillaDao
import com.dflch.water.caPlantillasDet.data.database.dao.PlantillaDetDao
import com.dflch.water.caTurnos.data.database.dao.TurnoDao
import com.dflch.water.caUsers.data.database.dao.UserDao
import com.dflch.water.core.WaterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideUserDao(waterDatabase: WaterDatabase): UserDao {
        return waterDatabase.userDao()
    }


    @Provides
    fun provideItemDao(waterDatabase: WaterDatabase): ItemDao {
        return waterDatabase.itemDao()
    }

    @Provides
    fun provideTurnoDao(waterDatabase: WaterDatabase): TurnoDao {
        return waterDatabase.turnoDao()
    }

    @Provides
    fun providePlantillaDao(waterDatabase: WaterDatabase): PlantillaDao {
        return waterDatabase.plantillaDao()
    }

    @Provides
    fun providePlantillaDetDao(waterDatabase: WaterDatabase): PlantillaDetDao {
        return waterDatabase.plantillaDetDao()
    }

    @Provides
    @Singleton
    fun provideWaterDatabase(@ApplicationContext appContext: Context): WaterDatabase {
        return Room.databaseBuilder(appContext, WaterDatabase::class.java, "EmcMovilDataBase").build()
    }
}
package com.dflch.water.caItems.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.dflch.water.caItems.data.database.entities.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity ORDER BY itemId ASC")
    fun getAllItems(): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items: List<ItemEntity>)

    @Update
    suspend fun updateItem(item: ItemEntity)

    @Update
    suspend fun updateAllItems(items: List<ItemEntity>)

    @Upsert
    suspend fun upsertItem(items: ItemEntity)

    @Upsert
    suspend fun upsertAllItems(items: List<ItemEntity>)

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Query("DELETE FROM ItemEntity")
    suspend fun deleteAllItems()

    @Query("DELETE FROM ItemEntity WHERE itemId NOT IN (:itemID)")
    suspend fun deleteItemList(itemID: List<Int>)

    @Query("SELECT COUNT(*) FROM ItemEntity WHERE  itemCodi = :itemCodi")
    suspend fun getCountItem(itemCodi: Int): Int

    @Query("SELECT COUNT(*) FROM ItemEntity")
    suspend fun count(): Int

    //Consultas por servicio
    @Query("SELECT * FROM ItemEntity WHERE itemEmpresa = 'AC' ORDER BY itemId ASC")
    fun getItemsActo(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM ItemEntity WHERE itemEmpresa = 'AL' ORDER BY itemId ASC")
    fun getItemsAlcant(): Flow<List<ItemEntity>>

}
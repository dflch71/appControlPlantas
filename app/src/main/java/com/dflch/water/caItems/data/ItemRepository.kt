package com.dflch.water.caItems.data

import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.data.network.ItemService
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val api: ItemService,
    private val itemDao: ItemDao
) {

    suspend fun getAllItemsFromApi(): List<ItemModel> {
        val response: List<Item> = api.getAllItems()
        return response.map { it.toDomain() }
    }

    val items: Flow<List<ItemModel>> =
        itemDao.getAllItems().map {
                items -> items.map {
                    it.toDomain()
                }
        }

    suspend fun addItem(itemModel: ItemModel) {
        itemDao.addItem(itemModel.toData())
    }

    suspend fun updateItems(itemModel: ItemModel) {
        itemDao.updateItem(itemModel.toData())
    }

    suspend fun deleteItems(itemModel: ItemModel) {
        itemDao.deleteItem(itemModel.toData())
    }

    suspend fun deleteAllItems() {
        itemDao.deleteAllItems()
    }

    suspend fun getCountItem(itemCodi: Int) : Int {
        return itemDao.getCountItem(itemCodi)
    }

    private fun ItemModel.toData(): ItemEntity {
        return ItemEntity(
            0,
            this.itemId,
            this.itemCodi,
            this.itemCosa,
            this.itemUnMe,
            this.itemDesc,
            this.itemUn,
            this.itemValor,
            this.itemEmpresa
        )
    }

}
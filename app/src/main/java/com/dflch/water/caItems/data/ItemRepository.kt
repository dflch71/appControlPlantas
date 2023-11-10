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

    suspend fun updateItem(itemModel: ItemModel) {
        itemDao.updateItem(itemModel.toData())
    }

    suspend fun deleteItem(itemModel: ItemModel) {
        itemDao.deleteItem(itemModel.toData())
    }

    suspend fun deleteAllItems() {
        itemDao.deleteAllItems()
    }

    suspend fun getCountItem(itemCodi: Int) : Int {
        return itemDao.getCountItem(itemCodi)
    }

    suspend fun count():Int {
        return itemDao.count()
    }

    suspend fun insertAllItems(items: List<ItemModel>) {
        itemDao.insertAllItems(items.map { it.toData() })
    }

    suspend fun requestItems(){
        val isDBEmpty = itemDao.count() == 0
        if (isDBEmpty){
            insertAllItems(getAllItemsFromApi())
        } else {
            val items = getAllItemsFromApi()
            for (i in items.indices){
                if (itemDao.getCountItem(items[i].itemCodi) == 0){
                    addItem(items[i])
                } else {
                    updateItem(items[i])
                }
            }
        }
    }

    private fun ItemModel.toData(): ItemEntity {
        return ItemEntity(
            this.id,
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
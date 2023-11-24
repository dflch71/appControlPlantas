package com.dflch.water.caItems.data

import com.dflch.water.caItems.data.database.dao.ItemDao
import com.dflch.water.caItems.data.database.entities.ItemEntity
import com.dflch.water.caItems.data.model.Item
import com.dflch.water.caItems.data.network.ItemService
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.model.toDomain
import com.dflch.water.caUsers.ui.model.UserModel
import kotlinx.coroutines.delay
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

    val itemsActo: Flow<List<ItemModel>> =
        itemDao.getItemsActo().map {
                items -> items.map {
            it.toDomain()
        }
        }

    val itemsAlcant: Flow<List<ItemModel>> =
        itemDao.getItemsAlcant().map {
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

    suspend fun deleteItemList(itemModel: List<ItemModel>) {
        val itemID = itemModel.map { it.itemId }
        itemDao.deleteItemList(itemID)
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

    private suspend fun updateAllItems(items: List<ItemModel>) {
        itemDao.updateAllItems(items.map { it.toData() })
    }

    private suspend fun upsertItem(items: ItemModel) {
        itemDao.upsertItem(items.toData())
    }

    private suspend fun upsertAllItems(items: List<ItemModel>) {
        itemDao.upsertAllItems(items.map { it.toData() })
    }

    suspend fun requestItems(){
        val isDBEmpty = count() == 0
        if (isDBEmpty){
            insertAllItems(getAllItemsFromApi())
        } else {
            //Sincronizar BD de la nube y local, eliminando los items
            deleteItemList(getAllItemsFromApi())
            //Modificar los items, con la informacion de la nube
            upsertAllItems(getAllItemsFromApi())
        }
    }

    suspend fun requestUpdateItems(){
        val listItems = getAllItemsFromApi()
        if (listItems.isNotEmpty()){
            updateAllItems(listItems)
        }

    }

    private fun ItemModel.toData(): ItemEntity {
        return ItemEntity(
            //this.id,
            this.itemId,
            this.itemCodi,
            this.itemCosa,
            this.itemUnMe,
            this.itemDesc,
            this.itemUn,
            this.itemValor,
            this.itemEmpresa,
            this.itemCantidad,
            this.itemValorTotal
        )
    }

}
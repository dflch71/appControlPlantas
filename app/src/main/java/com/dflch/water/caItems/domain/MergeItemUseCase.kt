package com.dflch.water.caItems.domain

import com.dflch.water.caItems.data.ItemRepository
import javax.inject.Inject

class MergeItemUseCase @Inject constructor(private val itemRepository: ItemRepository){
    /*
    suspend operator fun invoke() {
        val isDBEmpty = itemRepository.count() == 0
        if (isDBEmpty){
            itemRepository.insertAllItems(itemRepository.getAllItemsFromApi())
        }
        else {
            val items = itemRepository.getAllItemsFromApi()
            for (i in items.indices){
                if (itemRepository.getCountItem(items[i].itemCodi) == 0){
                    itemRepository.addItem(items[i])
                } else {
                    itemRepository.updateItem(items[i])
                }
            }
        }
    }
    */

    suspend operator fun invoke() {

        itemRepository.requestItems()

        //val isDBEmpty = itemRepository.count() == 0
        //if (!isDBEmpty) {
        //    itemRepository.deleteAllItems()
        //}
        //itemRepository.insertAllItems(itemRepository.getAllItemsFromApi())



        /*val listItems = itemRepository.getAllItemsFromApi()
        val isDBEmpty = itemRepository.count() == 0
        if (isDBEmpty){
            itemRepository.insertAllItems(listItems)
        }
        else {
            for (i in listItems.indices){
                if (itemRepository.getCountItem(listItems[i].itemCodi) == 0){
                    itemRepository.addItem(listItems[i])
                } else {
                    itemRepository.updateItem(listItems[i])
                }
            }
        }*/
    }

}
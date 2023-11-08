package com.dflch.water.caItems.domain

import com.dflch.water.caItems.data.ItemRepository
import javax.inject.Inject

class MergeItemUseCase @Inject constructor(private val itemRepository: ItemRepository){
    suspend operator fun invoke(){
        val isDBEmpty = itemRepository.count() == 0
        if (isDBEmpty){
            itemRepository.insertAllItems(itemRepository.getAllItemsFromApi())
        } else {
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
}
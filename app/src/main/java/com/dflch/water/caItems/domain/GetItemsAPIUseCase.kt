package com.dflch.water.caItems.domain

import android.util.Log
import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caItems.ui.model.ItemModel
import javax.inject.Inject

class GetItemsAPIUseCase @Inject constructor(private val itemRepository: ItemRepository){

    suspend operator fun invoke(){
        val items = itemRepository.getAllItemsFromApi()

        if (items.isNotEmpty()){
            for (item in items) {

                if (itemRepository.getCountItem(item.itemCodi) == 0) {
                    itemRepository.addItem(
                        ItemModel(
                            item.id,
                            item.itemId,
                            item.itemCodi,
                            item.itemCosa,
                            item.itemUnMe,
                            item.itemDesc,
                            item.itemUn,
                            item.itemValor,
                            item.itemEmpresa,
                            item.itemCantidad,
                            item.itemValorTotal
                        )
                    )
                } else {
                    itemRepository.updateItem(
                        ItemModel(
                            item.id,
                            item.itemId,
                            item.itemCodi,
                            item.itemCosa,
                            item.itemUnMe,
                            item.itemDesc,
                            item.itemUn,
                            item.itemValor,
                            item.itemEmpresa,
                            item.itemCantidad,
                            item.itemValorTotal
                        )
                    )
                }
            }
        } else { itemRepository.items }
    }
}


package com.dflch.water.caItems.domain

import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caItems.ui.model.ItemModel
import javax.inject.Inject

class UpdateItemUseCase @Inject constructor(private val itemRepository: ItemRepository){
    suspend operator fun invoke(itemModel: ItemModel){
        itemRepository.updateItem(itemModel)
    }
}
package com.dflch.water.caItems.domain

import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caItems.ui.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemsAPIUseCase_ @Inject constructor(private val itemRepository: ItemRepository){

    suspend operator fun invoke(): List<ItemModel> {

        return withContext(Dispatchers.IO) {
            itemRepository.getAllItemsFromApi()
        }

    }
}


package com.dflch.water.caItems.domain

import com.dflch.water.caItems.data.ItemRepository
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caUsers.ui.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsAlcantUseCase @Inject constructor(private val itemRepository: ItemRepository) {
    operator fun invoke(): Flow<List<ItemModel>> = itemRepository.itemsAlcant
}
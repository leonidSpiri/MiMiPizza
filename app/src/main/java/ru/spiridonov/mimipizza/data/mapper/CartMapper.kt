package ru.spiridonov.mimipizza.data.mapper

import ru.spiridonov.mimipizza.data.database.cart.CartDbModel
import ru.spiridonov.mimipizza.domain.entity.CartItem
import javax.inject.Inject

class CartMapper @Inject constructor() {
    fun mapEntityToDbModel(entity: CartItem) = CartDbModel(
        uniqueId = entity.uniqueId,
        id = entity.id,
        category = entity.category,
        size = entity.size,
        pizzaTypeDough = entity.pizzaTypeDough,
        count = entity.count,
    )

    fun mapDbModelToEntity(dbModel: CartDbModel) = CartItem(
        uniqueId = dbModel.uniqueId,
        id = dbModel.id,
        category = dbModel.category,
        size = dbModel.size,
        pizzaTypeDough = dbModel.pizzaTypeDough,
        count = dbModel.count,
    )
}
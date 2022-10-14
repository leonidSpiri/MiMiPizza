package ru.spiridonov.mimipizza.domain.use_cases.cart_item

import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.domain.repository.CartRepository
import javax.inject.Inject

class InsertCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartItem) = repository.insertCartItem(cartItem)
}
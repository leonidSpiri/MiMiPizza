package ru.spiridonov.mimipizza.domain.use_cases.cart_item

import ru.spiridonov.mimipizza.domain.repository.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(category: String, id: Int) = repository.deleteCartItem(category, id)

}
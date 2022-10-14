package ru.spiridonov.mimipizza.domain.use_cases.cart_item

import ru.spiridonov.mimipizza.domain.repository.CartRepository
import javax.inject.Inject

class GetCartListUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke() = repository.getCartList()
}
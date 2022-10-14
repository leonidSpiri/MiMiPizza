package ru.spiridonov.mimipizza.presentation.ui.cart

import androidx.lifecycle.ViewModel
import ru.spiridonov.mimipizza.domain.use_cases.cart_item.GetCartListUseCase
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getCartListUseCase: GetCartListUseCase
) : ViewModel() {
    fun getCartList() = getCartListUseCase()
}
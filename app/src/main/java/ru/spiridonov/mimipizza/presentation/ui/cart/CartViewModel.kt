package ru.spiridonov.mimipizza.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.domain.use_cases.cart_item.DeleteCartItemUseCase
import ru.spiridonov.mimipizza.domain.use_cases.cart_item.GetCartListUseCase
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getCartListUseCase: GetCartListUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase
) : ViewModel() {
    fun getCartList() = getCartListUseCase()

    fun deleteCartItem(item: CartItem) =
        viewModelScope.launch {
            deleteCartItemUseCase(item.uniqueId)
        }
}
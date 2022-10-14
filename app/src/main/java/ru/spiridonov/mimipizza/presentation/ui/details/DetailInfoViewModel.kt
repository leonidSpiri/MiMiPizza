package ru.spiridonov.mimipizza.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.domain.use_cases.cart_item.InsertCartItemUseCase
import javax.inject.Inject

class DetailInfoViewModel @Inject constructor(
    private val insertCartItemUseCase: InsertCartItemUseCase
) : ViewModel() {
    fun addMenuItemToCart(cartItem: CartItem) =
        viewModelScope.launch {
            insertCartItemUseCase(cartItem)
        }
}
package ru.spiridonov.mimipizza.presentation.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.domain.use_cases.cart_item.InsertCartItemUseCase
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.GetMenuListUseCase
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getMenuListUseCase: GetMenuListUseCase,
    private val insertCartItemUseCase: InsertCartItemUseCase
) : ViewModel() {

    fun getMenuList() = getMenuListUseCase()

    fun addMenuItemToCart(cartItem: CartItem) =
        viewModelScope.launch {
            insertCartItemUseCase(cartItem)
        }
}
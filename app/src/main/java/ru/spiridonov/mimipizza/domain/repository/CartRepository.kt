package ru.spiridonov.mimipizza.domain.repository

import androidx.lifecycle.LiveData
import ru.spiridonov.mimipizza.domain.entity.CartItem

interface CartRepository {

    fun getCartList(): LiveData<List<CartItem>>

    fun getCartItem(category: String, id: Int): LiveData<CartItem>

    suspend fun insertCartItem(cartItem: CartItem)

    fun deleteAllCart()

    fun deleteCartItem(category: String, id: Int)
}
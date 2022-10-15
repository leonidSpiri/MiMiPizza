package ru.spiridonov.mimipizza.data.repository

import android.util.Log
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.data.database.cart.CartDbModel
import ru.spiridonov.mimipizza.data.database.cart.CartListDao
import ru.spiridonov.mimipizza.data.mapper.CartMapper
import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartListDao: CartListDao,
    private val cartMapper: CartMapper
) : CartRepository {
    override fun getCartList() =
        Transformations.map(cartListDao.getCartList()) {
            it.map { item ->
                cartMapper.mapDbModelToEntity(item)
            }
        }

    override fun getCartItem(category: String, id: Int) =
        Transformations.map(cartListDao.getCartItem(category, id)) {
            cartMapper.mapDbModelToEntity(it)
        }

    override suspend fun insertCartItem(cartItem: CartItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val cartDbModel: CartDbModel? = cartListDao.getStaticCartItem(
                cartItem.category,
                cartItem.id,
                cartItem.size
            )

            val oldItem = cartDbModel?.let {
                cartMapper.mapDbModelToEntity(
                    it
                )
            }
            Log.d("CartRepositoryImpl", "insertCartItem: $oldItem")
            if (oldItem == null || oldItem.size != cartItem.size)
                cartListDao.insertCartItem(cartMapper.mapEntityToDbModel(cartItem))
            else cartListDao.insertCartItem(
                cartMapper.mapEntityToDbModel(
                    oldItem.copy(
                        count = oldItem.count + cartItem.count
                    )
                )
            )
        }
    }

    override suspend fun deleteAllCart() {
        CoroutineScope(Dispatchers.IO).launch {
            cartListDao.deleteAllCart()
        }
    }

    override suspend fun deleteCartItem(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            cartListDao.deleteCartItem(id)
        }
    }
}
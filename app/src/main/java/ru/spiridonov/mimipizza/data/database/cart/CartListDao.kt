package ru.spiridonov.mimipizza.data.database.cart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartListDao {
    @Query("SELECT * FROM cart_list")
    fun getCartList(): LiveData<List<CartDbModel>>

    @Query("SELECT * FROM cart_list WHERE category == :category AND id ==:id LIMIT 1")
    fun getCartItem(category: String, id: Int): LiveData<CartDbModel>

    @Query("SELECT * FROM cart_list WHERE category == :category AND id ==:id AND size ==:size LIMIT 1")
    fun getStaticCartItem(category: String, id: Int, size:String): CartDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartDbModel: CartDbModel)

    @Query("DELETE FROM cart_list")
    fun deleteAllCart()

    @Query("DELETE FROM cart_list where uniqueId ==:id")
    fun deleteCartItem(id: Int)
}

package ru.spiridonov.mimipizza.data.database.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_list")
data class CartDbModel(
    @PrimaryKey(autoGenerate = true)
    val uniqueId: Int,
    val id: Int,
    val category: String,
    val size: String,
    val count: Int,
    val pizzaTypeDough: String? = null
)

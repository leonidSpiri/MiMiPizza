package ru.spiridonov.mimipizza.data.database.menu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_list")
data class MenuDbModel(
    @PrimaryKey(autoGenerate = true)
    val uniqueId: Int,
    val id: Int,
    val category: String,
    val name: String,
    val size: String? = null,
    val pizzaTypeDough: String? = null,
    val weight: String? = null,
    val countOfAvailable: Int,
    val description: String,
    val price: String,
    val imageUrl: String
)

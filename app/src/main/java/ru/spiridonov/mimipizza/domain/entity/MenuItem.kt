package ru.spiridonov.mimipizza.domain.entity

data class MenuItem(
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
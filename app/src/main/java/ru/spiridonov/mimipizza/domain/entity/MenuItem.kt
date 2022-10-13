package ru.spiridonov.mimipizza.domain.entity

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val category: String
)
package ru.spiridonov.mimipizza.domain.entity

data class CartItem(
    val id: Int,
    val category: String,
    val size: String,
    val count: Int,
    val pizzaTypeDough: String? = null,
    var uniqueId: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
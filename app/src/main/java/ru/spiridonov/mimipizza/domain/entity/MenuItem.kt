package ru.spiridonov.mimipizza.domain.entity

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
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
    val imageUrl: String,
    var uniqueId: Int = UNDEFINED_ID
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
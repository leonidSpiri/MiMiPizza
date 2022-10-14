package ru.spiridonov.mimipizza.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DrinkInfoDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("weight")
    @Expose
    val weight: String? = null,
    @SerializedName("countOfAvailable")
    @Expose
    val countOfAvailable: Int,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("price")
    @Expose
    val price: Int? = null,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null
)

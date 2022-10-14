package ru.spiridonov.mimipizza.data.network.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PizzaInfoDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("size")
    @Expose
    val size: JsonObject? = null,
    @SerializedName("pizzaTypeDough")
    @Expose
    val pizzaTypeDough: JsonArray? = null,
    @SerializedName("weight")
    @Expose
    val weight: JsonObject? = null,
    @SerializedName("countOfAvailable")
    @Expose
    val countOfAvailable: Int,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("price")
    @Expose
    val price: JsonObject? = null,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null
)

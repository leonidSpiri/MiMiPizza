package ru.spiridonov.mimipizza.data.network.model

import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MenuJsonContainerDto(
    @SerializedName("menu")
    @Expose
    val json: JsonArray? = null
)

package ru.spiridonov.mimipizza.data.mapper

import android.util.Log
import com.google.gson.Gson
import ru.spiridonov.mimipizza.data.network.model.*
import javax.inject.Inject

class DtoMapper @Inject constructor() {
    fun mapPizzaJsonContainerToListPizzaInfo(jsonContainer: PizzaJsonContainerDto): List<PizzaInfoDto> {
        val result = mutableListOf<PizzaInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        jsonObject.forEach {
            it.asJsonObject?.let { jsonElement ->
                val menuItem = Gson().fromJson(jsonElement, PizzaInfoDto::class.java)
                result.add(menuItem)
            }
        }
        Log.d("TAG", "mapPizzaJsonContainerToListPizzaInfo: $result")
        return result
    }

    fun mapDessertJsonContainerToListDessertInfo(jsonContainer: DessertJsonContainerDto): List<DessertInfoDto> {
        val result = mutableListOf<DessertInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        jsonObject.forEach {
            it.asJsonObject?.let { jsonElement ->
                val menuItem = Gson().fromJson(jsonElement, DessertInfoDto::class.java)
                result.add(menuItem)
            }
        }
        return result
    }

    fun mapDrinkJsonContainerToListDrinkInfo(jsonContainer: DrinkJsonContainerDto): List<DrinkInfoDto> {
        val result = mutableListOf<DrinkInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        jsonObject.forEach {
            it.asJsonObject?.let { jsonElement ->
                val menuItem = Gson().fromJson(jsonElement, DrinkInfoDto::class.java)
                result.add(menuItem)
            }
        }
        return result
    }
}
package ru.spiridonov.mimipizza.data.mapper

import com.google.gson.JsonObject
import ru.spiridonov.mimipizza.data.network.model.DessertInfoDto
import ru.spiridonov.mimipizza.data.network.model.DrinkInfoDto
import ru.spiridonov.mimipizza.data.network.model.PizzaInfoDto
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import javax.inject.Inject

class MenuMapper @Inject constructor() {
    fun mapPizzaJsonContainerToMenuList(pizzaList: List<PizzaInfoDto>): List<MenuItem> {
        val result = mutableListOf<MenuItem>()
        pizzaList.forEach {
            result.add(
                MenuItem(
                    id = it.id,
                    category = it.category,
                    name = it.name,
                    size = parseJsonObjectToString(it.size),
                    pizzaTypeDough = it.pizzaTypeDough?.asJsonArray?.joinToString { dough -> dough.asString },
                    weight = parseJsonObjectToString(it.weight),
                    countOfAvailable = it.countOfAvailable,
                    description = it.description,
                    price = parseJsonObjectToString(it.price),
                    imageUrl = it.imageUrl.toString()
                )
            )
        }
        return result
    }


    fun mapDessertJsonContainerToMenuList(dessertList: List<DessertInfoDto>): List<MenuItem> {
        val result = mutableListOf<MenuItem>()
        dessertList.forEach {
            result.add(
                MenuItem(
                    id = it.id,
                    category = it.category,
                    name = it.name,
                    size = it.size,
                    countOfAvailable = it.countOfAvailable,
                    description = it.description,
                    price = it.price.toString(),
                    imageUrl = it.imageUrl.toString()
                )
            )
        }
        return result
    }


    fun mapDrinkJsonContainerToMenuList(drinkList: List<DrinkInfoDto>): List<MenuItem> {
        val result = mutableListOf<MenuItem>()
        drinkList.forEach {
            result.add(
                MenuItem(
                    id = it.id,
                    category = it.category,
                    name = it.name,
                    weight = it.weight,
                    countOfAvailable = it.countOfAvailable,
                    description = it.description,
                    price = it.price.toString(),
                    imageUrl = it.imageUrl.toString()
                )
            )
        }
        return result
    }


    private fun parseJsonObjectToString(jsonObject: JsonObject?): String {
        var size = ""
        jsonObject?.keySet()?.forEach { key ->
            size += " $key:${jsonObject.get(key).asString} "
        }
        return size.trim()
    }
}
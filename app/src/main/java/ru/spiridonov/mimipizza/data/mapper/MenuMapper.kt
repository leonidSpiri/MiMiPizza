package ru.spiridonov.mimipizza.data.mapper

import com.google.gson.JsonObject
import ru.spiridonov.mimipizza.data.database.MenuDbModel
import ru.spiridonov.mimipizza.data.network.model.DessertInfoDto
import ru.spiridonov.mimipizza.data.network.model.DrinkInfoDto
import ru.spiridonov.mimipizza.data.network.model.PizzaInfoDto
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import javax.inject.Inject

class MenuMapper @Inject constructor() {

    fun mapDtoToDbModel(entity: MenuItem) = MenuDbModel(
        uniqueId = entity.uniqueId,
        id = entity.id,
        category = entity.category,
        name = entity.name,
        size = entity.size,
        pizzaTypeDough = entity.pizzaTypeDough,
        weight = entity.weight,
        countOfAvailable = entity.countOfAvailable,
        description = entity.description,
        price = entity.price,
        imageUrl = entity.imageUrl
    )

    fun mapDbModelToEntity(dbModel: MenuDbModel) = MenuItem(
        uniqueId = dbModel.uniqueId,
        id = dbModel.id,
        category = dbModel.category,
        name = dbModel.name,
        size = dbModel.size,
        pizzaTypeDough = dbModel.pizzaTypeDough,
        weight = dbModel.weight,
        countOfAvailable = dbModel.countOfAvailable,
        description = dbModel.description,
        price = dbModel.price,
        imageUrl = dbModel.imageUrl
    )

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


    private fun parseJsonObjectToString(jsonObject: JsonObject?): String {
        var size = ""
        jsonObject?.keySet()?.forEach { key ->
            size += " $key:${jsonObject.get(key).asString} "
        }
        return size.trim()
    }
}
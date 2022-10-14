package ru.spiridonov.mimipizza.data.network

import retrofit2.http.GET
import ru.spiridonov.mimipizza.data.network.model.DessertJsonContainerDto
import ru.spiridonov.mimipizza.data.network.model.DrinkJsonContainerDto
import ru.spiridonov.mimipizza.data.network.model.PizzaJsonContainerDto


interface ApiService {

    @GET("api/pizza.json")
    suspend fun getPizzaList(
    ): PizzaJsonContainerDto

    @GET("api/dessert.json")
    suspend fun getDessertList(
    ): DessertJsonContainerDto

    @GET("api/drink.json")
    suspend fun getDrinkList(
    ): DrinkJsonContainerDto

}
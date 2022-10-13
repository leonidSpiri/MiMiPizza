package ru.spiridonov.mimipizza.data.network

import retrofit2.http.GET
import ru.spiridonov.mimipizza.data.network.model.MenuJsonContainerDto


interface ApiService {

    @GET("menu.json")
    suspend fun getMenuList(
    ): MenuJsonContainerDto

}
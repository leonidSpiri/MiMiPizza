package ru.spiridonov.mimipizza.data.repository

import ru.spiridonov.mimipizza.data.mapper.DtoMapper
import ru.spiridonov.mimipizza.data.mapper.MenuMapper
import ru.spiridonov.mimipizza.data.network.ApiService
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dtoMapper: DtoMapper,
    private val menuMapper: MenuMapper
) : MenuRepository {

    override suspend fun getMenuList(): List<MenuItem> {
        val pizzaJsonContainer = apiService.getPizzaList()
        val pizzaInfoDtoList = dtoMapper.mapPizzaJsonContainerToListPizzaInfo(pizzaJsonContainer)
        val mapPizza = menuMapper.mapPizzaJsonContainerToMenuList(pizzaInfoDtoList)

        val dessertJsonContainer = apiService.getDessertList()
        val dessertInfoDtoList =
            dtoMapper.mapDessertJsonContainerToListDessertInfo(dessertJsonContainer)
        val mapDessert = menuMapper.mapDessertJsonContainerToMenuList(dessertInfoDtoList)

        val drinkJsonContainer = apiService.getDrinkList()
        val drinkInfoDtoList = dtoMapper.mapDrinkJsonContainerToListDrinkInfo(drinkJsonContainer)
        val mapDrink = menuMapper.mapDrinkJsonContainerToMenuList(drinkInfoDtoList)

        return mapPizza + mapDessert + mapDrink
    }

    override fun getMenuItemById(category: String, id: Int): MenuItem {
        TODO("Not yet implemented")
    }
}
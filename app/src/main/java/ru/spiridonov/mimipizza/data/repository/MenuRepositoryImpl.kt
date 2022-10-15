package ru.spiridonov.mimipizza.data.repository

import android.app.Application
import android.content.Intent
import androidx.lifecycle.Transformations
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.data.database.menu.MenuListDao
import ru.spiridonov.mimipizza.data.mapper.DtoMapper
import ru.spiridonov.mimipizza.data.mapper.MenuMapper
import ru.spiridonov.mimipizza.data.network.ApiService
import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val application: Application,
    private val menuListDao: MenuListDao,
    private val apiService: ApiService,
    private val dtoMapper: DtoMapper,
    private val menuMapper: MenuMapper
) : MenuRepository {

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(application)
    }

    override fun getMenuList() =
        Transformations.map(menuListDao.getMenuList()) {
            it.map { item ->
                menuMapper.mapDbModelToEntity(item)
            }
        }


    override fun getMenuItemById(category: String, id: Int) =
        Transformations.map(menuListDao.getMenuItem(category, id)) {
            menuMapper.mapDbModelToEntity(it)
        }

    override fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Intent("loaded").apply {
                    putExtra("status", "in_progress")
                    localBroadcastManager.sendBroadcast(this)
                }
                val pizzaJsonContainer = apiService.getPizzaList()
                val pizzaInfoDtoList =
                    dtoMapper.mapPizzaJsonContainerToListPizzaInfo(pizzaJsonContainer)
                val mapPizza = menuMapper.mapPizzaJsonContainerToMenuList(pizzaInfoDtoList)

                val dessertJsonContainer = apiService.getDessertList()
                val dessertInfoDtoList =
                    dtoMapper.mapDessertJsonContainerToListDessertInfo(dessertJsonContainer)
                val mapDessert = menuMapper.mapDessertJsonContainerToMenuList(dessertInfoDtoList)

                val drinkJsonContainer = apiService.getDrinkList()
                val drinkInfoDtoList =
                    dtoMapper.mapDrinkJsonContainerToListDrinkInfo(drinkJsonContainer)
                val mapDrink = menuMapper.mapDrinkJsonContainerToMenuList(drinkInfoDtoList)
                val menuItem = mapPizza + mapDessert + mapDrink
                if (menuItem.isNotEmpty()) {
                    menuListDao.deleteAll()
                    menuListDao.insertMenuList(menuItem.map { menuMapper.mapEntityToDbModel(it) })
                    Intent("loaded").apply {
                        putExtra("status", "success")
                        localBroadcastManager.sendBroadcast(this)
                    }
                    return@launch
                }
                Intent("loaded").apply {
                    putExtra("status", "error")
                    localBroadcastManager.sendBroadcast(this)
                }
            } catch (e: Exception) {
                Intent("loaded").apply {
                    putExtra("status", "error")
                    localBroadcastManager.sendBroadcast(this)
                }
            }
        }
    }
}
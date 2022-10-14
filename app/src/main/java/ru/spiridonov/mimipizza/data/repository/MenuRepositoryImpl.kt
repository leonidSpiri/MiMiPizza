package ru.spiridonov.mimipizza.data.repository

import android.util.Log
import ru.spiridonov.mimipizza.data.mapper.DtoMapper
import ru.spiridonov.mimipizza.data.network.ApiService
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dtoMapper: DtoMapper
) : MenuRepository {

    override suspend fun getMenuList(): List<MenuItem> {
        val jsonContainer = apiService.getMenuList()
        val menuInfoDtoList = dtoMapper.mapJsonContainerToListCoinInfo(jsonContainer)
        Log.d("MenuRepositoryImpl", "menuInfoDtoList = $menuInfoDtoList")
        return emptyList()

    }

    override fun getMenuItemById(id: Int): MenuItem {
        TODO("Not yet implemented")
    }
}
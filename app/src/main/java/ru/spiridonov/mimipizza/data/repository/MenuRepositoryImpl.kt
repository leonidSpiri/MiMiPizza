package ru.spiridonov.mimipizza.data.repository

import ru.spiridonov.mimipizza.domain.entity.MenuItem
import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
) : MenuRepository {
    override fun getMenuList(): List<MenuItem> {
        TODO("Not yet implemented")
    }

    override fun getMenuItemById(id: Int): MenuItem {
        TODO("Not yet implemented")
    }
}
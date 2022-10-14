package ru.spiridonov.mimipizza.domain.repository

import ru.spiridonov.mimipizza.domain.entity.MenuItem


interface MenuRepository {
    suspend fun getMenuList(): List<MenuItem>
    fun getMenuItemById(category: String, id: Int): MenuItem
}
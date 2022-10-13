package ru.spiridonov.mimipizza.domain.repository

import ru.spiridonov.mimipizza.domain.entity.MenuItem


interface MenuRepository {
    fun getMenuList(): List<MenuItem>
    fun getMenuItemById(id: Int): MenuItem
}
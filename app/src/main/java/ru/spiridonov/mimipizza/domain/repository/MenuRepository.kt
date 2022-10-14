package ru.spiridonov.mimipizza.domain.repository

import androidx.lifecycle.LiveData
import ru.spiridonov.mimipizza.domain.entity.MenuItem


interface MenuRepository {
    fun getMenuList(): LiveData<List<MenuItem>>
    fun getMenuItemById(category: String, id: Int): LiveData<MenuItem>
    fun loadData()
}
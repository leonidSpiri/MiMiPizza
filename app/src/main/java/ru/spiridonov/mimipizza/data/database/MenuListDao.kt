package ru.spiridonov.mimipizza.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuListDao {
    @Query("SELECT * FROM menu_list")
    fun getMenuList(): LiveData<List<MenuDbModel>>

    @Query("SELECT * FROM menu_list WHERE category == :category AND id ==:id LIMIT 1")
    fun getMenuItem(category: String, id: Int): LiveData<MenuDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuList(priceList: List<MenuDbModel>)
}

package ru.spiridonov.mimipizza.data.mapper

import android.util.Log
import com.google.gson.Gson
import ru.spiridonov.mimipizza.data.network.model.MenuInfoDto
import ru.spiridonov.mimipizza.data.network.model.MenuJsonContainerDto
import javax.inject.Inject

class DtoMapper @Inject constructor() {
    fun mapJsonContainerToListCoinInfo(jsonContainer: MenuJsonContainerDto): List<MenuInfoDto> {
        val result = mutableListOf<MenuInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        jsonObject.forEach {
            it.asJsonObject?.let { jsonElement ->
                val menuItem = Gson().fromJson(jsonElement, MenuInfoDto::class.java)
                Log.d("DtoMapper", "mapJsonContainerToListCoinInfo: $menuItem")
                result.add(menuItem)
            }
        }
        return result
    }
}
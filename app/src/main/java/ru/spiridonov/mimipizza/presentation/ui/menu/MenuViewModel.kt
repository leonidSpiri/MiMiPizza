package ru.spiridonov.mimipizza.presentation.ui.menu

import androidx.lifecycle.ViewModel
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.GetMenuListUseCase
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getMenuListUseCase: GetMenuListUseCase
) : ViewModel() {

    fun getMenuList() = getMenuListUseCase()
}
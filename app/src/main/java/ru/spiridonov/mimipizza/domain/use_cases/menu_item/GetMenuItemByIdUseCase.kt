package ru.spiridonov.mimipizza.domain.use_cases.menu_item

import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class GetMenuItemByIdUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    operator fun invoke(category: String, id: Int) = repository.getMenuItemById(category, id)

}
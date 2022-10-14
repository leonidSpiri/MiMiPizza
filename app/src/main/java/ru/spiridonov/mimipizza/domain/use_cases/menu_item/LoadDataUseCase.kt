package ru.spiridonov.mimipizza.domain.use_cases.menu_item

import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    operator fun invoke() = repository.loadData()

}
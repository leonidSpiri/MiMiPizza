package ru.spiridonov.mimipizza.presentation.viewmodels

import androidx.lifecycle.ViewModel
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.LoadDataUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {
    fun loadData() = loadDataUseCase()
}
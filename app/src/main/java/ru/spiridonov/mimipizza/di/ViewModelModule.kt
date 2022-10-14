package ru.spiridonov.mimipizza.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.spiridonov.mimipizza.presentation.ui.account.AccountViewModel
import ru.spiridonov.mimipizza.presentation.ui.cart.CartViewModel
import ru.spiridonov.mimipizza.presentation.ui.details.DetailInfoViewModel
import ru.spiridonov.mimipizza.presentation.ui.menu.MenuViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailInfoViewModel::class)
    fun bindDetailInfoViewModel(viewModel: DetailInfoViewModel): ViewModel
}
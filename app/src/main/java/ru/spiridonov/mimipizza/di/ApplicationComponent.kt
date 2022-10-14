package ru.spiridonov.mimipizza.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.presentation.MainActivity
import ru.spiridonov.mimipizza.presentation.ui.account.AccountFragment
import ru.spiridonov.mimipizza.presentation.ui.cart.CartFragment
import ru.spiridonov.mimipizza.presentation.ui.details.DetailInfoActivity
import ru.spiridonov.mimipizza.presentation.ui.menu.MenuFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailInfoActivity)
    fun inject(fragment: AccountFragment)
    fun inject(fragment: CartFragment)
    fun inject(fragment: MenuFragment)
    fun inject(application: MiMiPizzaApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
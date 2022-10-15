package ru.spiridonov.mimipizza.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.spiridonov.mimipizza.data.database.cart.CartAppDatabase
import ru.spiridonov.mimipizza.data.database.cart.CartListDao
import ru.spiridonov.mimipizza.data.database.menu.MenuAppDatabase
import ru.spiridonov.mimipizza.data.database.menu.MenuListDao
import ru.spiridonov.mimipizza.data.network.ApiFactory
import ru.spiridonov.mimipizza.data.network.ApiService
import ru.spiridonov.mimipizza.data.repository.CartRepositoryImpl
import ru.spiridonov.mimipizza.data.repository.MenuRepositoryImpl
import ru.spiridonov.mimipizza.domain.repository.CartRepository
import ru.spiridonov.mimipizza.domain.repository.MenuRepository
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.GetMenuItemByIdUseCase

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMenuListRepository(impl: MenuRepositoryImpl): MenuRepository

    @Binds
    @ApplicationScope
    fun bindCartListRepository(impl: CartRepositoryImpl): CartRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        @ApplicationScope
        fun provideMenuListDao(
            application: Application
        ): MenuListDao {
            return MenuAppDatabase.getInstance(application).menuListDao()
        }

        @Provides
        @ApplicationScope
        fun provideCartListDao(
            application: Application
        ): CartListDao {
            return CartAppDatabase.getInstance(application).cartListDao()
        }
    }
}
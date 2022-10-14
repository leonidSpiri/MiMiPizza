package ru.spiridonov.mimipizza.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.spiridonov.mimipizza.data.database.AppDatabase
import ru.spiridonov.mimipizza.data.database.MenuListDao
import ru.spiridonov.mimipizza.data.network.ApiFactory
import ru.spiridonov.mimipizza.data.network.ApiService
import ru.spiridonov.mimipizza.data.repository.MenuRepositoryImpl
import ru.spiridonov.mimipizza.domain.repository.MenuRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMenuListRepository(impl: MenuRepositoryImpl): MenuRepository


    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        @ApplicationScope
        fun provideCurrListDao(
            application: Application
        ): MenuListDao {
            return AppDatabase.getInstance(application).menuListDao()
        }
    }
}
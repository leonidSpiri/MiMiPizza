package ru.spiridonov.mimipizza.di

import dagger.Binds
import dagger.Module
import ru.spiridonov.mimipizza.data.repository.MenuRepositoryImpl
import ru.spiridonov.mimipizza.domain.repository.MenuRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMenuListRepository(impl: MenuRepositoryImpl): MenuRepository


    /* companion object {
         @Provides
         @ApplicationScope
         fun provideCurrListDao(
             application: Application
         ): CurrListDao {
             return AppDatabase.getInstance(application).currListDao()
         }
     }*/
}
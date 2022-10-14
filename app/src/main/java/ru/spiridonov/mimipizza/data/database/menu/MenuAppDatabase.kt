package ru.spiridonov.mimipizza.data.database.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuDbModel::class], version = 1, exportSchema = false)
abstract class MenuAppDatabase : RoomDatabase() {
    companion object {

        private var db: MenuAppDatabase? = null
        private const val DB_NAME = "menu.db"
        private val LOCK = Any()

        fun getInstance(context: Context): MenuAppDatabase {
            db?.let {
                return it
            }
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        MenuAppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun menuListDao(): MenuListDao
}

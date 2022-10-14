package ru.spiridonov.mimipizza.data.database.cart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartDbModel::class], version = 1, exportSchema = false)
abstract class CartAppDatabase : RoomDatabase() {
    companion object {

        private var db: CartAppDatabase? = null
        private const val DB_NAME = "cart.db"
        private val LOCK = Any()

        fun getInstance(context: Context): CartAppDatabase {
            db?.let {
                return it
            }
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        CartAppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun cartListDao(): CartListDao
}

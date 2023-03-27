package com.jephtecolin.varoflix.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jephtecolin.varoflix.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class VaroflixRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: VaroflixRoomDatabase? = null

        fun getDatabase(context: Context): VaroflixRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VaroflixRoomDatabase::class.java,
                    "varo_flix_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
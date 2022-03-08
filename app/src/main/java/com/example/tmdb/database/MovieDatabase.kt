package com.example.tmdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase(){

    /*
    @Inject
    lateinit var context: Context
     */

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE:MovieDatabase? = null
        fun getFavouritesDatabase(context: Context):MovieDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        "favourites")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}

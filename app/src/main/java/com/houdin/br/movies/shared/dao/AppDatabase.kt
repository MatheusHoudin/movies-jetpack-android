package com.houdin.br.movies.shared.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.houdin.br.movies.shared.model.MovieEntity

/**
 * @author Matheus Gomes on 13/06/2021.
 */
@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}

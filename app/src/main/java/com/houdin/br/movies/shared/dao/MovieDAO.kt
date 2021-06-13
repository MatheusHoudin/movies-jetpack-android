package com.houdin.br.movies.shared.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.houdin.br.movies.shared.model.MovieEntity

/**
 * @author Matheus Gomes on 13/06/2021.
 */
@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieEntity>
}

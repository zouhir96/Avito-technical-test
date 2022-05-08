package com.zrcoding.android_challenge.data.local.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zrcoding.android_challenge.data.local.entities.MovieEntity

@Dao
interface MovieDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getAll(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY release_date ASC")
    fun getAllOrderedByNameAsc(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY release_date DESC")
    fun getAllOrderedByNameDesc(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY release_date DESC")
    fun getAllOrderedByDateAsc(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY release_date DESC")
    fun getAllOrderedByDateDesc(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun searchMovies(query: String): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :query ORDER BY release_date ASC")
    fun searchMoviesOrderedByNameAsc(query: String): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :query ORDER BY release_date DESC")
    fun searchMoviesOrderedByNameDesc(query: String): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :query ORDER BY release_date ASC")
    fun searchMoviesOrderedByDateAsc(query: String): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :query ORDER BY release_date DESC")
    fun searchMoviesOrderedByDateDesc(query: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clear()
}
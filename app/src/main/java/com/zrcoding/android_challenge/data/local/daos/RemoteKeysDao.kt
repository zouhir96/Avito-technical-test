package com.zrcoding.android_challenge.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zrcoding.android_challenge.data.local.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeyEntity: List<RemoteKeysEntity>)

    @Query("SELECT * FROM page_info WHERE movie_id = :movieId")
    suspend fun remoteKeysMovieId(movieId: Long): RemoteKeysEntity?

    @Query("DELETE FROM page_info")
    suspend fun clear()
}
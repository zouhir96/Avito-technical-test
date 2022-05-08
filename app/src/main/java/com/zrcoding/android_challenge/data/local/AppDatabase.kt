package com.zrcoding.android_challenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zrcoding.android_challenge.data.local.daos.MovieDao
import com.zrcoding.android_challenge.data.local.daos.RemoteKeysDao
import com.zrcoding.android_challenge.data.local.entities.MovieEntity
import com.zrcoding.android_challenge.data.local.entities.RemoteKeysEntity

@Database(
    version = 1,
    entities = [MovieEntity::class, RemoteKeysEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getMovieDao(): MovieDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}
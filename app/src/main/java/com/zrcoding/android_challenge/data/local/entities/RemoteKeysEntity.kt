package com.zrcoding.android_challenge.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_info")
data class RemoteKeysEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Long,
    @ColumnInfo(name = "prev_key") val prevKey: Int?,
    @ColumnInfo(name = "next_key") val nextKey: Int?
)
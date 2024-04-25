package com.sopt.now.data

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_list")
data class Friend(
    @DrawableRes @ColumnInfo(name = "profile_image") val profileImage: Int,
    @ColumnInfo(name = "friend_name") val name: String,
    @ColumnInfo(name = "friend_music") val music: String,
){
    @PrimaryKey(autoGenerate = true) var friend_id: Int = 0
}
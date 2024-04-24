package com.sopt.now.data

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes @ColumnInfo(name = "profile_image") val profileImage: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "self_description") val selfDescription: String,
)
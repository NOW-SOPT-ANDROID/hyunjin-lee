package com.sopt.now.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_data")
data class UserData(
    @ColumnInfo(name = "user_id") val userid: String,
    @ColumnInfo(name = "user_pw") val userpw: String,
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "user_mbti") val usermbti: String,
    @ColumnInfo(name = "user_image") val userprofileImage: Int?,
): Parcelable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
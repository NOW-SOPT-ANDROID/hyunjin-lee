package com.sopt.now.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val id: String,
    val pw: String,
    val name: String,
    val mbti: String
): Parcelable
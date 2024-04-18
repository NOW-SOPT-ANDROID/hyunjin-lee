package com.sopt.now.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val id: String,
    val pw: String,
    val name: String,
    val mbti: String,
    @DrawableRes val profileImage: Int,
): Parcelable
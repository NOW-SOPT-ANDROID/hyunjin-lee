package com.sopt.now.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    @DrawableRes val profileImage: Int,
    val id: String,
    val pw: String,
    val name: String,
    val mbti: String,
): Parcelable
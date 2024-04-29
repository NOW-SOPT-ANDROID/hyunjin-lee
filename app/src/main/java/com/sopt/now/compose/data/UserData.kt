package com.sopt.now.compose.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class UserData(
    var id: String,
    var pw: String,
    val name: String,
    val mbti: String,
): Parcelable

package com.sopt.now

import android.app.Application
import androidx.room.Room
import com.sopt.now.data.Friend.FriendDatabase
import com.sopt.now.data.Friend.FriendDatabase.Companion.MIGRATION_1_2_
import com.sopt.now.data.PreferenceUtil
import com.sopt.now.data.User.UserDatabase
import com.sopt.now.data.User.UserDatabase.Companion.MIGRATION_1_2
class MyApplication : Application() {
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()

        user_database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "user_database"
        )
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2) // 필요한 마이그레이션 추가
            .build()

        friend_database = Room.databaseBuilder(
            applicationContext,
            FriendDatabase::class.java, "friend_list"
        )
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2_) // 필요한 마이그레이션 추가
            .build()
    }

    companion object {
        lateinit var prefs: PreferenceUtil
        lateinit var user_database: UserDatabase
        lateinit var friend_database: FriendDatabase
    }
}
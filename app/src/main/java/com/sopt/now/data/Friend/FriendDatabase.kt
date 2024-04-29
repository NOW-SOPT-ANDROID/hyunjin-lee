package com.sopt.now.data.Friend

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Friend::class], version = 2, exportSchema = false)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        val MIGRATION_1_2_: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 기존 테이블 삭제
                database.execSQL("DROP TABLE IF EXISTS friend_list")
                // 새로운 구조의 테이블 생성
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `friend_list` (
                        `friend_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `friend_name` TEXT NOT NULL,
                        `profile_image` INTEGER NOT NULL,
                        `friend_music` TEXT NOT NULL
                    )
                """.trimIndent())
            }
        }

        @Volatile
        private var INSTANCE: FriendDatabase? = null

        fun getDatabase(context: Context): FriendDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendDatabase::class.java,
                    "friend_database"
                ).addMigrations(MIGRATION_1_2_)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

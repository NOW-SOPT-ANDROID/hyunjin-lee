package com.sopt.now.data.User

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UserData::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // 테이블 존재 여부 확인
                val cursor = db.query("SELECT name FROM sqlite_master WHERE type='table' AND name='user_data'")
                val exists = cursor.moveToFirst()
                cursor.close()

                if (!exists) {
                    // user_data 테이블이 존재하지 않는 경우, 테이블 생성 로직 추가
                    db.execSQL("""
                        CREATE TABLE IF NOT EXISTS `user_data` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            `user_id` TEXT NOT NULL,
                            `user_pw` TEXT NOT NULL,
                            `user_name` TEXT NOT NULL,
                            `user_mbti` TEXT NOT NULL,
                            `user_image` INTEGER
                        )
                    """)
                } else {
                    // 테이블이 이미 존재하면, 새로운 열 추가
                    db.execSQL("ALTER TABLE user_data ADD COLUMN user_flag INTEGER DEFAULT 0")
                }
            }
        }

        @Volatile
        private var INSTANCE: UserDatabase? = null

        @Synchronized
        fun getUserDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).addMigrations(MIGRATION_1_2) // 마이그레이션 추가
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

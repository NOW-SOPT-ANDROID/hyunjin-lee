package com.sopt.now.data.User

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userData: UserData): Long

    @Update
    fun update(profile: UserData)

    @Delete
    fun delete(profile: UserData)

    @Query("SELECT * FROM user_data")
    fun getAllUserInfo(): LiveData<UserData>

    @Query("DELETE FROM user_data")
    fun deleteUserByName()

    @Query("SELECT * FROM user_data WHERE user_id = :userId")
    suspend fun getUserById(userId: String): UserData?

    @Query("SELECT * FROM user_data")
    fun getAllUserListInfo(): LiveData<List<UserData>>
}
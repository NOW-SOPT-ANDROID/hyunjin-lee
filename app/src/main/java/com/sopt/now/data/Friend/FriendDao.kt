package com.sopt.now.data.Friend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FriendDao {
    @Query("SELECT * FROM friend_list")
    fun getAllFriends(): LiveData<List<Friend>>

    // suspend 함수로 변경
    @Query("SELECT * FROM friend_list")
    suspend fun getAllFriendsList(): List<Friend>

    // 친구 정보 DB 삽입
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriend(friend: Friend)

    // 친구 정보 DB 삭제
    @Delete
    suspend fun deleteFriend(friend: Friend)
}
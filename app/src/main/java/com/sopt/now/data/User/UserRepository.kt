package com.sopt.now.data.User

import com.sopt.now.data.User.UserDao
import com.sopt.now.data.User.UserData

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: UserData) = userDao.insertUser(user)

    // RoomDB로부터 사용자 정보 가져오기
    suspend fun getUserInfo(id: String): UserData? = userDao.getUserById(id)

    // 아이디로 사용자 찾기
    suspend fun findUserById(userId: String): UserData? = userDao.getUserById(userId)
}

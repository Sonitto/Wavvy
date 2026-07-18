package com.example.module.home.activity.data

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 22:36
 */
data class UserDetail (
    val code: Int,
    val profile: Profile?
)

data class Profile(
    val userId: Long,
    val nickname: String,
    val avatarUrl: String
)
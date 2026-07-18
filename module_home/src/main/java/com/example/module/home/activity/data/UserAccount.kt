package com.example.module.home.activity.data

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 22:35
 */
data class UserAccount(
    val code: Int,
    val account: Account?
)

data class Account(
    val id : Long?,
    val userName: String?
)

package com.example.module.login.data

/**
 * description ：游客登录数据类
 * author : Cherry77551
 * date : 2026/7/17 17:27
 */
data class Guest(
    val code: Int,
    val userId: Long,
    val createTime: Long,
    val cookie: String?
)
package com.example.musicPlayer.data

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/24 16:27
 */
data class Comment(
    val user: User,
    val content: String,
    val timeStr: String,
    val likedCount: Int,
    val commentId: Long
)


data class CommentResponse(
    val hotComments: List<Comment>,
    val comments: List<Comment>,
    val total: Int,
    val more: Boolean
)

data class User(
    val nickname: String,
    val avatarUrl: String
)
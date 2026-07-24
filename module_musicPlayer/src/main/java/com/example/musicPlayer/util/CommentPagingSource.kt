package com.example.musicPlayer.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.musicPlayer.api.CommentApi
import com.example.musicPlayer.data.Comment
import com.wavvy.net.RetrofitClient

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/24 17:25
 */
class CommentPagingSource(private val id: Long): PagingSource<Int, Comment>() {

    private val api by lazy{
        RetrofitClient.create(CommentApi::class.java)
    }

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            val response = api.getComment(id, offset, limit)
            val data = if (offset == 0) {
                (response.hotComments ?: emptyList()) + (response.comments ?: emptyList())
            } else {
                response.comments ?: emptyList()
            }
            LoadResult.Page(
                data = data,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (response.more) offset + limit else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
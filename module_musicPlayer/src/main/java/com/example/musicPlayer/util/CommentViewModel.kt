package com.example.musicPlayer.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.musicPlayer.data.Comment
import kotlinx.coroutines.flow.Flow

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/24 17:52
 */
class CommentViewModel : ViewModel() {
    fun getComments(id: Long): Flow<PagingData<Comment>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { CommentPagingSource(id) }
        ).flow
            .cachedIn(viewModelScope)
    }
}
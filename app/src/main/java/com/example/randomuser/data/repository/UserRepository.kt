package com.example.randomuser.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.randomuser.data.datasource.remote.ApiService
import com.example.randomuser.data.datasource.remote.paging.UsersPagingDataSource
import com.example.randomuser.data.model.Results
import com.example.randomuser.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun userPagingDataSource(email: String?) = Pager(
        pagingSourceFactory = { UsersPagingDataSource(apiService, email) },
        config = PagingConfig(pageSize = 1)
    ).flow

    suspend fun userDetail(userId: String?): Flow<DataState<Results>> = flow {
        emit(DataState.Loading)
        try {
            apiService.userDetail("abc", 2,1,userId!!)
        }catch (e:java.lang.Exception){
            Timber.e(e.message)
        }

    }

}
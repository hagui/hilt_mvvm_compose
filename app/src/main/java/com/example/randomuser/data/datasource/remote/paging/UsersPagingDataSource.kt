package com.example.randomuser.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomuser.data.datasource.remote.ApiService
import com.example.randomuser.data.model.Results
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class UsersPagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val email: String?
) :
    PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextPage = params.key ?: 1
            val userList = apiService.searchUsers("ABC", nextPage, 10)
            LoadResult.Page(
                data = userList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey =  if (userList.results.isNotEmpty())  {
                    userList.info?.page?.plus(1)
                }  else  1
            )
        } catch (exception: IOException) {
            Timber.e("exception ${exception.message}")
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            return LoadResult.Error(httpException)
        }
    }
}
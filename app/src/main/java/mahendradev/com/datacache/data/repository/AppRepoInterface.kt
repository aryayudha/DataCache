package mahendradev.com.datacache.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import mahendradev.com.datacache.api.ApiError
import mahendradev.com.datacache.data.database.Post

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
interface AppRepoInterface {

    fun getPosts(
        success: (List<Post>) -> Unit,
        failure: (ApiError) -> Unit = {}
    ): Disposable

    fun insertPost(posts: Post): Disposable

    fun getPostDB(success: (List<Post>) -> Unit): Disposable

    //fun queryPostDB(success: (LiveData<List<Post>>) -> Unit): Disposable
}
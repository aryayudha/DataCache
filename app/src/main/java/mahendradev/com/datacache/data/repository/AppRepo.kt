package mahendradev.com.datacache.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mahendradev.com.datacache.api.ApiDisposable
import mahendradev.com.datacache.api.ApiError
import mahendradev.com.datacache.api.ApiService
import mahendradev.com.datacache.data.database.Post
import mahendradev.com.datacache.data.database.PostDatabase

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class AppRepo(
    private val apiService: ApiService,
    private val database: PostDatabase
): AppRepoInterface {

    override fun getPosts(success: (List<Post>) -> Unit, failure: (ApiError) -> Unit): Disposable {
        return apiService
            .getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                ApiDisposable<List<Post>>({
                    success(it)
                    Log.d("Post AppRepo","getPosts API: $it")
                }
                    ,failure
                )
            )
    }

    override fun insertPost(posts: Post): Disposable =
        Observable.fromCallable {
            database.postsDao().insertData(posts)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d("Post AppRepo","insert post : $it")
            }

    override fun getPostDB(success: (List<Post>) -> Unit): Disposable =
        Observable.fromCallable {
            database.postsDao().getAllTitle()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                success(it)
                Log.d("Post AppRepo","get post DB: $it")
            }

//    override fun queryPostDB(success: (LiveData<List<Post>>) -> Unit): Disposable =
//        Observable.fromCallable {
//            database.postsDao().queryTitle("ad")
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{
//                success(it)
//                Log.d("Post AppRepo","query post : $it")
//            }

}
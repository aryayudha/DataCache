package mahendradev.com.datacache.mvp.presenter

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import mahendradev.com.datacache.data.database.Post
import mahendradev.com.datacache.data.repository.AppRepoInterface
import mahendradev.com.datacache.mvp.contract.PostListContract

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostListPresenter(private val appRepoInterface: AppRepoInterface) : PostListContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private lateinit var view: PostListContract.View

    override fun loadPosts() {
        Log.d("Post presenter","load repo")
        appRepoInterface.getPosts(
            {
                //view.showProgressDialog(false)
                //view.loadPostsSuccess(it as MutableList<Posts>)
                if (it.isNotEmpty()){
                    Log.d("Post presenter","api post dataSize" + it.size)
                    insertToDB(it as MutableList<Post>)

                }
            },{
                Log.d("Post presenter","api error")
                //view.showProgressDialog(false)
                //view.showErrorMessage(it.getErrorMessage())
            }
        ).also {
            if (it != null){
                subscriptions.add(it)
            }
        }
    }

    private fun insertToDB(result: MutableList<Post>){
        for (post in result){
            appRepoInterface.insertPost(post).also {
                subscriptions.add(it)
            }
        }
    }

    override fun showPost() {
        appRepoInterface.getPostDB {
            if (it.isNotEmpty()){
                view.showProgressDialog(false)
                view.loadPostsSuccess(it as MutableList<Post>)
                Log.d("Post presenter","getPostDB succes" + it.size)
            }else{
                view.showProgressDialog(false)
                view.showErrorMessage("DB is empty")
            }
        }.also {
            if (it != null){
                subscriptions.add(it)
            }
        }
    }

//    override fun queryPost() {
//        appRepoInterface.queryPostDB {
//            //if (it.value!!.isNotEmpty()){
//                Log.d("nilai","getPostDB succes" + it.value.toString())
//            //}
//        }.also { if (it != null){
//            subscriptions.add(it)
//        }
//        }
//    }
    override fun subscribe() {

    }

    override fun unsubcribe() {
        subscriptions.clear()
    }

    override fun attach(view: PostListContract.View) {
        this.view = view
    }

}
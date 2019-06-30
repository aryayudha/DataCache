package mahendradev.com.datacache.mvp.contract

import mahendradev.com.datacache.data.database.Post

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostListContract {

    interface View : BaseContract.View {
        fun showProgressDialog(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadPostsSuccess(list: MutableList<Post>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadPosts()
        fun showPost()
        //fun queryPost()
    }

}
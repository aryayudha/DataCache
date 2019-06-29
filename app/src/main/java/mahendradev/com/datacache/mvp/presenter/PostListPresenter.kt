package mahendradev.com.datacache.mvp.presenter

import io.reactivex.disposables.CompositeDisposable
import mahendradev.com.datacache.data.repository.AppRepoInterface
import mahendradev.com.datacache.mvp.contract.PostListContract

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostListPresenter(val appRepoInterface: AppRepoInterface) : PostListContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private lateinit var view: PostListContract.View

    override fun loadPosts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPost() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {

    }

    override fun unsubcribe() {
        subscriptions.clear()
    }

    override fun attach(view: PostListContract.View) {
        this.view = view
    }

}
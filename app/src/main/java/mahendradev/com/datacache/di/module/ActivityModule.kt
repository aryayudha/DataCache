package mahendradev.com.datacache.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import mahendradev.com.datacache.data.repository.AppRepoInterface
import mahendradev.com.datacache.mvp.contract.PostListContract
import mahendradev.com.datacache.mvp.presenter.PostListPresenter

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity():Activity{
        return activity
    }

    @Provides
    fun providePostsPresenter(appRepoInterface: AppRepoInterface): PostListContract.Presenter{
        return PostListPresenter(appRepoInterface)
    }
}
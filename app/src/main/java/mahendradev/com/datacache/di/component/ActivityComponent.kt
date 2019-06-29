package mahendradev.com.datacache.di.component

import dagger.Component
import mahendradev.com.datacache.di.module.ActivityModule
import mahendradev.com.datacache.di.module.AppModule
import mahendradev.com.datacache.di.module.DataModule
import mahendradev.com.datacache.ui.PostListActivity
import javax.inject.Singleton

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Singleton
@Component(modules = [ActivityModule::class, AppModule::class, DataModule::class])
interface ActivityComponent {

    fun inject(postListActivity: PostListActivity)

}
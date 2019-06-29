package mahendradev.com.datacache.di.component

import dagger.Component
import mahendradev.com.datacache.AppBase
import mahendradev.com.datacache.di.module.AppModule
import javax.inject.Singleton

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(appBase: AppBase)

}
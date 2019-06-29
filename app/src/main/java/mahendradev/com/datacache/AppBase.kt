package mahendradev.com.datacache

import android.app.Application
import mahendradev.com.datacache.di.component.AppComponent
import mahendradev.com.datacache.di.component.DaggerAppComponent
import mahendradev.com.datacache.di.module.AppModule

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class AppBase: Application() {

    companion object {
        lateinit var instance: AppBase
    }

    //init di component
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        injectDependency()
    }

    @Suppress("DEPRECATION")
    fun injectDependency(){
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

}
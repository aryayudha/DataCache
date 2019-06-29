package mahendradev.com.datacache.di.module

import dagger.Module
import dagger.Provides
import mahendradev.com.datacache.AppBase
import javax.inject.Singleton

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Module
class AppModule(private val appBase: AppBase) {

    @Provides
    @Singleton
    fun provideApp():AppBase{
        return appBase
    }
}
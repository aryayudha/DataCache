package mahendradev.com.datacache.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import mahendradev.com.datacache.AppBase
import mahendradev.com.datacache.api.ApiService
import mahendradev.com.datacache.data.database.PostDao
import mahendradev.com.datacache.data.database.PostDatabase
import mahendradev.com.datacache.data.repository.AppRepo
import mahendradev.com.datacache.data.repository.AppRepoInterface
import javax.inject.Singleton

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Module
class DataModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Provides
    @Singleton
    fun providePostDatabase(appBase: AppBase): PostDatabase {
        return Room
            .databaseBuilder(appBase, PostDatabase::class.java, PostDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    fun providePostDao(postDatabase: PostDatabase): PostDao {
        return postDatabase.postsDao()
    }

    @Provides
    fun provideRepository(apiService: ApiService, postDatabase: PostDatabase):AppRepoInterface{
        return AppRepo(apiService,postDatabase)
    }
}
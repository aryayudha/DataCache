package mahendradev.com.datacache.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Database(entities = [Post::class],version = 1)

abstract class PostDatabase : RoomDatabase(){
    abstract fun postsDao(): PostDao

    companion object{
        const val DB_NAME = "posts.db"

    }
}
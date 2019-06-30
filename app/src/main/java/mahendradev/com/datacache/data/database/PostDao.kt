package mahendradev.com.datacache.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Dao
interface PostDao {

    @Query("SELECT * from post ORDER BY title ASC")
    fun getAllTitle(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(post: Post)

    @Query("SELECT * from post WHERE title LIKE:query ")
    fun queryTitle(query: String): LiveData<List<Post>>
}
package mahendradev.com.datacache.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
@Entity
data class Post(
    @PrimaryKey
    @ColumnInfo(name = "userId") val userId : Int,
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "body") val body : String)

package mahendradev.com.datacache.api

import io.reactivex.Observable
import mahendradev.com.datacache.data.database.Post
import mahendradev.com.datacache.util.Constants
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
interface ApiService {

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    companion object {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
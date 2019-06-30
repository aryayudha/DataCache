package mahendradev.com.datacache.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_post.*
import kotlinx.android.synthetic.main.activity_main.*
import mahendradev.com.datacache.R
import mahendradev.com.datacache.util.InternetCheck

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        title = "Detail Post"
        post_title.text = intent.getStringExtra("title")
        post_body.text = intent.getStringExtra("body")
    }
}
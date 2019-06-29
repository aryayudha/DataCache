package mahendradev.com.datacache.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mahendradev.com.datacache.AppBase
import mahendradev.com.datacache.util.InternetCheck
import mahendradev.com.datacache.R
import mahendradev.com.datacache.data.database.Post
import mahendradev.com.datacache.data.database.PostDatabase
import mahendradev.com.datacache.di.component.DaggerActivityComponent
import mahendradev.com.datacache.di.module.ActivityModule
import mahendradev.com.datacache.di.module.AppModule
import mahendradev.com.datacache.di.module.DataModule
import mahendradev.com.datacache.mvp.contract.PostListContract
import javax.inject.Inject

class PostListActivity : AppCompatActivity(), PostListContract.View {


    @Inject lateinit var presenter: PostListContract.Presenter
    private var isConnected = false
    private lateinit var searchView: SearchView
    private val postAdapter: PostAdapter = PostAdapter(arrayListOf())
    //private val postDB: PostDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this@PostListActivity)
        presenter.subscribe()
        presenter.loadPosts()

        isConnected = InternetCheck.isConnected(this)

        swipeLayout.setOnRefreshListener {
            //refresh()
            loadListPost()
            Log.e("Post Act", "refresh")
            swipeLayout.isRefreshing = false
        }
        loadListPost()
    }

    private fun injectDependency(){
        val postComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .appModule(AppModule(AppBase.instance))
            .dataModule(DataModule())
            .build()

        postComponent.inject(this)
    }


    override fun showProgressDialog(show: Boolean) {
        if (show){
            progress_circular.visibility = View.VISIBLE
        }else{
            progress_circular.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Load Post Error", error)
        pullToRefresh.text = "Please pull to refresh"
    }

    override fun loadPostsSuccess(posts: MutableList<Post>) {
        val mLayoutManager = LinearLayoutManager(this)
        rvListPost.layoutManager = mLayoutManager
        rvListPost.adapter = postAdapter

        if (posts.isNotEmpty()){
            Log.e("post list act","load post success" + posts.size)
            postAdapter.addData(posts)
            pullToRefresh.visibility = View.GONE
        }else{
            Log.e("post fragment","load post null or error")
        }
    }

    private fun loadListPost(){
        if (isConnected){
            showProgressDialog(true)
            Log.d("nilai","connect")
            //presenter.loadPosts()
        }else{
            showProgressDialog(true)
            presenter.showPost()
            Log.d("nilai","offline")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Search by title"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("nilai","text : $newText" )
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("nilai","text submit : $query" )
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}

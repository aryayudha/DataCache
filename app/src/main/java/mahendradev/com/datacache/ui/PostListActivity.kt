package mahendradev.com.datacache.ui

import android.content.Intent
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
import java.util.*
import javax.inject.Inject

class PostListActivity : AppCompatActivity(),
    PostListContract.View, PostAdapter.OnItemClickListener {

    @Inject lateinit var presenter: PostListContract.Presenter
    private var isConnected = false
    private lateinit var searchView: SearchView
    private val postAdapter: PostAdapter = PostAdapter(arrayListOf(),this)
    var dataPost = mutableListOf<Post>()

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
            //swipeLayout.isRefreshing = false
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
            Log.e("Postlist act","load post success" + posts.size)
            dataPost = posts
            postAdapter.addData(posts)
            pullToRefresh.visibility = View.GONE
        }else{
            Log.e("post fragment","load post null or error")
        }
    }

    private fun loadListPost(){//gak guna
        swipeLayout.isRefreshing = false
        showProgressDialog(true)
        presenter.showPost()
    }

    override fun itemDetail(tittle: String, body: String) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("title",tittle)
        intent.putExtra("body", body)
        startActivity(intent)
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
                Log.d("Post activity","text : $newText" )
                myFilter(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("Post activity","text submit : $query" )
                myFilter(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    fun myFilter(searchKey: String) {
        val listFiltered = mutableListOf<Post>()
        val newSearchKey = searchKey.toLowerCase()
        if (searchKey.isNotEmpty()) {
            for (i in 0 until dataPost.size-1) {
                val valuePerIndex = dataPost[i]
                Log.e("valuePerIndex : ", "$valuePerIndex")
                val titlePerIndex = valuePerIndex.title
                if (titlePerIndex.toLowerCase(Locale.getDefault()).contains(newSearchKey)) {
                    listFiltered.add(valuePerIndex)
                }
            }
            postAdapter.addData(listFiltered)
        }
    }
}

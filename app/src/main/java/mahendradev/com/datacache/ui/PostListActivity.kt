package mahendradev.com.datacache.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import mahendradev.com.datacache.AppBase
import mahendradev.com.datacache.util.InternetCheck
import mahendradev.com.datacache.R
import mahendradev.com.datacache.data.database.Post
import mahendradev.com.datacache.di.component.DaggerActivityComponent
import mahendradev.com.datacache.di.module.ActivityModule
import mahendradev.com.datacache.di.module.AppModule
import mahendradev.com.datacache.di.module.DataModule
import mahendradev.com.datacache.mvp.contract.PostListContract
import javax.inject.Inject

class PostListActivity : AppCompatActivity(), PostListContract.View {


    @Inject
    lateinit var presenter: PostListContract.Presenter
    private var isConnected = false
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isConnected = InternetCheck.isConnected(this)

        if (isConnected){
            Log.d("nilai","connect")
        }else{
            Log.d("nilai","offline")
        }

        injectDependency()

    }

    private fun injectDependency(){
        val postComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule())
            .appModule(AppModule(AppBase.instance))
            .dataModule(DataModule())
            .build()

        postComponent.inject(this)
    }


    override fun showProgressDialog(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPostsSuccess(list: MutableList<Post>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

package mahendradev.com.datacache.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mahendradev.com.datacache.R
import mahendradev.com.datacache.data.database.Post

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostAdapter(private val list: MutableList<Post>): RecyclerView.Adapter<PostAdapter.ListViewHolder>() {

    fun addData(list: MutableList<Post>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        //val postData = [""]

        val post = list[position]
        Log.e("nilai","adapter " + list.size)
        //holder.adapter_layout
        holder.postTitle.text = post.title
        //holder.postBody.text = post.body
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //var adapterLayout: LinearLayout = itemView.findViewById(R.id.list_item)
        var postTitle = itemView.findViewById(R.id.post_title) as TextView
        //var postBody: TextView = itemView.findViewById(R.id.post_body)
    }
}
package mahendradev.com.datacache.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mahendradev.com.datacache.R
import mahendradev.com.datacache.data.database.Post

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class PostAdapter(private val list: MutableList<Post>, activity: Activity): RecyclerView.Adapter<PostAdapter.ListViewHolder>() {

    private val listener: OnItemClickListener
    init {
        this.listener = activity as OnItemClickListener
    }

    interface OnItemClickListener {
        fun itemDetail(title : String, body: String)
    }

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

        val post = list[position]
        Log.e("Post Adapter","adapter " + list.size)
        holder.adapterLayout.setOnClickListener {

            val title = post.title
            val body = post.body
            listener.itemDetail(title, body)
        }
        holder.postTitle.text = post.title
        //holder.postBody.text = post.body
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var adapterLayout: LinearLayout = itemView.findViewById(R.id.list_item)
        var postTitle = itemView.findViewById(R.id.post_title) as TextView
        //var postBody: TextView = itemView.findViewById(R.id.post_body)
    }
}
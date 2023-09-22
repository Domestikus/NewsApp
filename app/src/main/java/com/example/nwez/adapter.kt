package com.example.nwez

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

class adapter(private val listener: onClick): RecyclerView.Adapter<viewHolder>() {

    val items=ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent,false)
        val ViewHolder= viewHolder(view)
        view.setOnClickListener(){
            listener.onClicked(items[ViewHolder.adapterPosition])
        }
        return ViewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val piece= items[position]
        holder.titleView.text= piece.title
        holder.author.text= piece.author

        Glide.with(holder.itemView.context).load(piece.imageUrl).into(holder.image)
    }

    fun updateData(newData: ArrayList<News>){
        items.clear()
        items.addAll(newData)

        notifyDataSetChanged()
    }
}

class viewHolder(itemView: View) : ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val author: TextView= itemView.findViewById(R.id.author)
    val image: ImageView= itemView.findViewById(R.id.image)
}

interface onClick{
    fun onClicked(news: News)
}








package com.example.nwez

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity(), onClick {

    private lateinit var Adaptor: adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)
        data()
        Adaptor = adapter(this)
        findViewById<RecyclerView>(R.id.recyclerView).adapter= Adaptor
    }

    fun data(){
        val queue = Volley.newRequestQueue(this)
        val url ="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray= ArrayList<News>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                Adaptor.updateData(newsArray)
            },
            {

            })
        queue.add(jsonObjectRequest)
    }

    override fun onClicked(news: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent= builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(news.url))
    }

}
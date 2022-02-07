package com.example.rtkl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rtkl.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var layoutManager: RecyclerView.LayoutManager
    private var articles = mutableListOf<Article>()
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {                                            //Binding the Data
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        adapter = NewsAdapter(this@MainActivity, articles)
        binding.newsList.adapter = adapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        binding.newsList.layoutManager = layoutManager
        binding.newsList.apply {
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

            getNews()
    }

    //Append new news to the current list
/*
    private fun appendNews(news: List<Article>) {
        val current = ArrayList(articles)
        current.addAll(news)
        this.articles = current
    }

    fun nextPage() {
        if ((newslistScrollPosition + 1 >= page_size * pageNum)) {

        }
    }

    private fun incrementPageNumber() {
        pageNum = pageNum + 1
    }

    fun onChangeScrollPostion(position: Int) {
        newslistScrollPosition = position
    }

    //--end--
    */
    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("onResponse", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Error in fetching News", t)
            }
        })
    }
}

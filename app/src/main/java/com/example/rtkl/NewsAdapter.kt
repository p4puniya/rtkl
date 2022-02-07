package com.example.rtkl

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

class NewsAdapter(val context: Context, val articles: List<Article>) :
    Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article= articles[position]
        holder.newsTitle.text = article.title
        holder.newsEditor.text= article.author
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.newsImage.setOnClickListener {
            Toast.makeText(context,article.title, Toast.LENGTH_SHORT).show()
        }
        holder.newsTitle.setOnClickListener{
            Toast.makeText(context,article.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<RoundedImageView>(R.id.cardViewImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.cardViewHeading)
        var newsEditor = itemView.findViewById<TextView>(R.id.cardViewEditor)
    }
}

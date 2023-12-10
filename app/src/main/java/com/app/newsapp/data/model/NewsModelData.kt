package com.app.newsapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("news_site") val newsSite: String,
    @SerializedName("featured") val featured: Boolean,
)

data class NewsResponse(
    @SerializedName("results") val results: List<News>
)

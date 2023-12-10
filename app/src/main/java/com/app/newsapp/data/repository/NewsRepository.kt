package com.app.newsapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.app.newsapp.api.NewsApiClient
import com.app.newsapp.api.service.NewsApiService
import com.app.newsapp.data.db.NewsDatabase
import com.app.newsapp.data.model.News
import com.app.newsapp.session.NewsSession
import java.util.Calendar


class NewsRepository(application: Application) {

    private val newsApiService: NewsApiService =
        NewsApiClient().retrofit.create(NewsApiService::class.java)
    private val newsDao = NewsDatabase.getDatabase(application).newsDao()

    private val session = NewsSession.getInstance(application)
    private var offset = session.getInt(NewsSession.lastFetchIndex)

    init {
        if(session.getString(NewsSession.currentDate)!=null) {
            if (session.getString(NewsSession.currentDate) != getCurrentDate()) {
                session.clear()
                session.setString(NewsSession.currentDate, getCurrentDate())
                offset = 0
            }
        }else{
            session.setString(NewsSession.currentDate, getCurrentDate())
        }
    }

    private suspend fun insertNews(news: List<News>) {
        newsDao.insertNewsData(news)
    }

    fun getAllNews(): LiveData<List<News>> {
        return newsDao.getAllNews()
    }

    suspend fun getNewsFromApi() {
        if(session.getBoolean(NewsSession.isComplete)){
            return
        }
        val response = newsApiService.getNewsResponse(offset = offset)
        insertNews(response.results)
        if (response.results.isNotEmpty()) {
            session.setInt(NewsSession.lastFetchIndex, offset + 20 + 1) // offset+ limit + 1
        } else {
            session.setBoolean(NewsSession.isComplete, true)
        }
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$day"
    }

}
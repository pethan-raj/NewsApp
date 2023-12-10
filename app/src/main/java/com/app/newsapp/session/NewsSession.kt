package com.app.newsapp.session

import android.app.Application
import android.content.Context

class NewsSession(application: Application) {

    companion object {

        const val lastFetchIndex: String = "lastFetchIndex"
        const val currentDate: String = "currentDate"
        const val isComplete: String = "isComplete"


        private var instance: NewsSession? = null
        fun getInstance(application: Application): NewsSession {
            if (instance == null) {
                instance = NewsSession(application)
            }
            return instance!!
        }
    }

    private val sharedPreferences = application.getSharedPreferences("news", Context.MODE_PRIVATE)

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}
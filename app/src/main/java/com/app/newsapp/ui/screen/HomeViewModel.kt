

package com.app.newsapp.ui.screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsapp.data.model.News
import com.app.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(application = app) {

    private val newsRepository = NewsRepository(app)

    private val _newsList: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    var newsList: StateFlow<List<News>> = _newsList

    private var tempList = listOf<News>()

    var searchText: MutableState<String> = mutableStateOf("")

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().observeForever {
                _newsList.value = it
                tempList = it
            }
        }
    }

    fun getNewsFromApi() {
        viewModelScope.launch {
            newsRepository.getNewsFromApi()
        }
    }

    fun searchTextChange(searchText: String) {
        this.searchText.value = searchText
        if (searchText.isEmpty()) {
            _newsList.value = tempList
            return
        }
        _newsList.value = _newsList.value.filter { news ->
            news.title.contains(searchText, ignoreCase = true)
        }
    }

}
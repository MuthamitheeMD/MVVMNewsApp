package com.example.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewsapp.model.Article
import com.example.mvvmnewsapp.model.NewsResponse
import com.example.mvvmnewsapp.repository.NewsRepository
import com.example.mvvmnewsapp.utils.Resource
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    init {
        getBreakingNews("ke")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch{
        breakingNews.postValue(Resource.Loading())
        val response =newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch{
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let{ resultResponse ->
                breakingNewsPage++
                if(breakingNewResponse == null){
                    breakingNewResponse = resultResponse
                }else{
                    val oldArticles = breakingNewResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let{ resultResponse ->
                searchNewsPage++
                if(searchNewsResponse == null){
                    searchNewsResponse = resultResponse
                }else{
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch{
        newsRepository.upsert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch{
        newsRepository.deleteArticle(article)
    }
    fun getSavedNews() = newsRepository.getSavedNews()


}
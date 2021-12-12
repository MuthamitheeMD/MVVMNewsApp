package com.example.mvvmnewsapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {

    ovveride fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T

    }

}
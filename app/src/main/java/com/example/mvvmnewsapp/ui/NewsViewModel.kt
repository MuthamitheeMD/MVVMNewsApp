package com.example.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmnewsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {


}
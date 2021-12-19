package com.example.mvvmnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmnewsapp.databinding.ActivityMainBinding
import com.example.mvvmnewsapp.repository.NewsRepository
import com.example.mvvmnewsapp.room.ArticleDatabase
import com.example.mvvmnewsapp.ui.NewsViewModel
import com.example.mvvmnewsapp.ui.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val bottomNav = binding.bottomNavView
        val navHostFrag = binding.navHost

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNav.setupWithNavController(navHostFrag.findNavController())
    }
}
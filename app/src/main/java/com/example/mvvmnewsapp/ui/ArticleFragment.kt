package com.example.mvvmnewsapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mvvmnewsapp.R


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}
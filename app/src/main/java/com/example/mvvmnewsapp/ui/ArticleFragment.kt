package com.example.mvvmnewsapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.mvvmnewsapp.MainActivity
import com.example.mvvmnewsapp.R
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}
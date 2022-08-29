package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityWebViewBinding
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity : BindingActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {

    private val infoUrl: String by bundleNonNull(WEB_VIEW_EXTRA)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@WebViewActivity
            urlLink = this@WebViewActivity.infoUrl
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            webView.apply {
                scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webViewClient = webViewClient()
                settings.setSupportZoom(true)
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                settings.javaScriptEnabled = true
                settings.cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                loadUrl(infoUrl)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun ActivityWebViewBinding.webViewClient() =
        object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
            }

            fun showLoading() {
                progressbar.visibility = View.VISIBLE
            }

            fun hideLoading() {
                if (progressbar.isVisible) {
                    progressbar.visibility = View.GONE
                }
            }
        }

    companion object {
        @VisibleForTesting
        const val WEB_VIEW_EXTRA = "WEB_VIEW_EXTRA"

        fun startActivity(view: View, url: String) {
            view.context.intentOf<WebViewActivity> {
                putExtra(WEB_VIEW_EXTRA, url)
                startActivity(view.context)
            }
        }
    }
}
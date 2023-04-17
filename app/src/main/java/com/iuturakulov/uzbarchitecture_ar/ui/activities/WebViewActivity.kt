package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityWebViewBinding
import com.skydoves.bindables.BindingActivity
import com.skydoves.bindables.binding
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BindingActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {

    private val infoUrl: String by bundleNonNull(WEB_VIEW_EXTRA)
    private val title: String by bundleNonNull(TITLE_VIEW_EXTRA)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding {
            lifecycleOwner = this@WebViewActivity
            urlLink = this@WebViewActivity.infoUrl
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.title = title
            webviewToolbar.setOnClickListener {
                onBackPressed()
            }
            // Apply WebView settings and set up the WebViewClient
            webView.apply {
                scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webViewClient = createWebViewClient()
                settings.apply {
                    setSupportZoom(true)
                    builtInZoomControls = true
                    displayZoomControls = false
                    javaScriptEnabled = true
                    cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
                    useWideViewPort = true
                    loadWithOverviewMode = true
                }

                loadUrl(infoUrl)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // Create a custom WebViewClient with loading indicators and error handling
    private fun ActivityWebViewBinding.createWebViewClient() =
        object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                Snackbar.make(
                    webView,
                    getString(R.string.webview_error, description ?: ""),
                    Snackbar.LENGTH_LONG
                ).show()
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

        @VisibleForTesting
        const val TITLE_VIEW_EXTRA = "TITLE_VIEW_EXTRA"

        fun startActivity(view: View, url: String, title: String) {
            view.context.intentOf<WebViewActivity> {
                putExtra(WEB_VIEW_EXTRA, url)
                putExtra(TITLE_VIEW_EXTRA, title)
                startActivity(view.context)
            }
        }
    }
}

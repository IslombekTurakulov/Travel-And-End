package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.lifecycleScope
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityDetailBinding
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.network.NetworkConnectivityObserver
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    @Inject
    lateinit var connectivityObserver: NetworkConnectivityObserver

    private val arch: ArchitectureInfo by bundleNonNull(ARCH_EXTRA)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DetailActivity
            component = this@DetailActivity.arch
            wikiBtn.setOnClickListener {
                WebViewActivity.startActivity(root, arch.wikipediaUrl)
            }
            arBtn.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(arch.arUrl)))
            }
        }
        connectivityObserver.observe().onEach {
            when (it.name) {
                "Available" -> {
                    Toast.makeText(this, "Connection Available", Toast.LENGTH_SHORT).show()
                    setActivationBtn(true)
                }
                "Unavailable" -> {
                    Toast.makeText(this, "Connection Unavailable", Toast.LENGTH_SHORT).show()
                    setActivationBtn(false)
                }
                else -> {
                    Toast.makeText(this, "Connection Lost", Toast.LENGTH_SHORT).show()
                    setActivationBtn(false)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setActivationBtn(flag: Boolean) {
        binding {
            arBtn.isClickable = flag
            wikiBtn.isClickable = flag
        }
    }

    companion object {
        @VisibleForTesting
        const val ARCH_EXTRA = "ARCH_EXTRA"

        fun startActivity(view: View, architecture: ArchitectureInfo) {
            view.context.intentOf<DetailActivity> {
                putExtra(ARCH_EXTRA, architecture)
                startActivity(view.context)
            }
        }
    }
}
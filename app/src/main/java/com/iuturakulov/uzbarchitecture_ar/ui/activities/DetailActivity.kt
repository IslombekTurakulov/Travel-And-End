package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityDetailBinding
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val arch: ArchitectureInfo by bundleNonNull("ARCH_EXTRA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DetailActivity
            component = this@DetailActivity.arch
        }

        binding.wikiBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(arch.wikipediaUrl)
                )
            )
        }

        binding.arBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        arch.arUrl
                    )
                )
            )
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
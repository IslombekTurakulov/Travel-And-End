package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityDetailBinding
import com.iuturakulov.uzbarchitecture_ar.ui.viewmodel.DetailViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    @VisibleForTesting
    val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, arch.name)
    }

    private val arch: Architecture by bundleNonNull("ARCH_EXTRA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DetailActivity
            component = this@DetailActivity.arch
            vm = viewModel
        }

        binding.wikiBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(viewModel.architectureInfo?.wikipediaUrl)
                )
            )
        }

        binding.arBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        viewModel.architectureInfo?.arUrl
                    )
                )
            )
        }
    }


    companion object {
        @VisibleForTesting
        const val ARCH_EXTRA = "ARCH_EXTRA"

        fun startActivity(view: View, architecture: Architecture) {
            view.context.intentOf<DetailActivity> {
                putExtra(ARCH_EXTRA, architecture)
                startActivity(view.context)
            }
        }
    }
}
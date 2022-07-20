package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.ar.core.Pose
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.ux.ArFragment
import androidx.annotation.VisibleForTesting
import com.iuturakulov.renderinglib.ModelRenderer
import com.iuturakulov.renderinglib.ArchitectureModels
import com.iuturakulov.renderinglib.ArchitectureModels.DEFAULT_POSITION_DETAILS_ARCHITECTURE
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityDetailBinding
import com.iuturakulov.uzbarchitecture_ar.extensions.findFragmentAs
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.ui.viewmodel.DetailViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    @VisibleForTesting
    val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, architecture.name)
    }

    private val architecture: Architecture by bundleNonNull("ARCH_EXTRA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            lifecycleOwner = this@DetailActivity
            archcomponent = this@DetailActivity.architecture
            vm = viewModel
        }

        with(findFragmentAs<ArFragment>(R.id.arFragment)) {
            planeDiscoveryController.hide()
            planeDiscoveryController.setInstructionView(null)
            arSceneView.planeRenderer.isVisible = false
            arSceneView.scene.addOnUpdateListener {
                onUpdate(it)
                // checks the state of the AR frame is Tracking.
                val arFrame = arSceneView.arFrame ?: return@addOnUpdateListener
                if (arFrame.camera?.trackingState != TrackingState.TRACKING) {
                    return@addOnUpdateListener
                }

                // initialize the global anchor with default rendering models.
                arSceneView.session.whatIfNotNull { session ->
                    initializeModels(this, session)
                }
            }
        }
    }

    private fun initializeModels(arFragment: ArFragment, session: Session) {
        if (session.allAnchors.isEmpty()) {
            val pose = Pose(floatArrayOf(0f, 0f, -1f), floatArrayOf(0f, 0f, 0f, 1f))
            session.createAnchor(pose).apply {
                val architecture = ArchitectureModels.getPokemonByName(this@DetailActivity.architecture!!.name)
                    .copy(localPosition = DEFAULT_POSITION_DETAILS_ARCHITECTURE)
                ModelRenderer.renderObject(this@DetailActivity, architecture) { renderable ->
                    ModelRenderer.addModelOnScene(arFragment, this, renderable, architecture)
                }
            }
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
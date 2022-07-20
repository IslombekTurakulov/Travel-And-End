package com.iuturakulov.renderinglib

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.MotionEvent
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlin.random.Random

object ModelRenderer {

  /**
   * Render a model using the [ModelRenderable].
   *
   * @param context Context for getting model source.
   * @param renderingModel A [RenderingModel] which has a rendering information.
   * @param thenAccept A lambda for executing if ready to use the rendered model.
   */
  inline fun renderObject(
    context: Context,
    renderingModel: RenderingModel,
    crossinline thenAccept: (Renderable) -> Unit
  ) {
    ModelRenderable.builder()
      .setSource(context, Uri.parse(renderingModel.model))
      .build()
      .thenAccept { thenAccept(it) }
      .exceptionally {
        AlertDialog.Builder(context)
          .setMessage(it.localizedMessage)
          .show()
        return@exceptionally null
      }
  }

  /**
   * Adds a  object to the AR scene.
   *
   * @param fragment The target [ArFragment] for adding a new node.
   * @param anchor An anchor for creating a new [AnchorNode].
   * @param renderable A [Renderable] for setting it to display for the [AnchorNode].
   * @param renderingModel A model information for displaying on the scene.
   */
  fun addModelOnScene(
    fragment: ArFragment,
    anchor: Anchor,
    renderable: Renderable,
    renderingModel: RenderingModel
  ) {
    val anchorNode = AnchorNode(anchor)
    TransformableNode(fragment.transformationSystem).apply {
      name = renderingModel.name
      localPosition = renderingModel.localPosition
      this.renderable = renderable
      translationController.isEnabled = false
      setParent(anchorNode)
      setLookDirection(renderingModel.direction)
      scaleController.minScale = renderingModel.scale
      scaleController.maxScale = renderingModel.scale + 0.05f
      fragment.arSceneView.scene.addChild(anchorNode)
      setOnTouchListener { hitTestResult, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_UP) {
          hitTestResult.node?.let { node ->
            node.setLookDirection(Vector3(0f, 0f, 1f))
            ModelAnimations.translateModel(
              anchorNode = node,
              targetPosition = Vector3(
                localPosition.x,
                localPosition.y + 0.25f,
                localPosition.z
              ),
              doWhenFinish = {
                val localPosition = renderingModel.localPosition
                ModelAnimations.translateModel(node, localPosition)
              }
            )
          }
        }
        true
      }
    }
  }

  /**
   * Returns a random float position between -0.5 to 0.5.
   *
   * @return A random float position between -0.5 to 0.5.
   */
  private fun getRandomPosition(): Float {
    val position = Random.nextFloat()
    return if (position <= 0.5f) {
      position
    } else {
      position - 1
    }
  }
}

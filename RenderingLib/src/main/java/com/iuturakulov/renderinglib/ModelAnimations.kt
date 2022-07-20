package com.iuturakulov.renderinglib

import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.QuaternionEvaluator
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.math.Vector3Evaluator

/** ModelAnimations is a helper class for executing animations to a target [Node]. */
object ModelAnimations {

  /**
   * Translates a target node to a desired position.
   *
   * @param anchorNode A target [Node] for translating.
   * @param targetPosition A target position for translating the [Node].
   * @param durationTime A duration time for executing this animation.
   * @param doWhenFinish A lambda for executing after finish this animation.
   */
  inline fun translateModel(
    anchorNode: com.google.ar.sceneform.Node,
    targetPosition: Vector3,
    durationTime: Long = 150L,
    crossinline doWhenFinish: () -> Unit = {}
  ) {
    ObjectAnimator().apply {
      setAutoCancel(false)
      target = anchorNode
      duration = durationTime
      setObjectValues(
        anchorNode.localPosition,
        targetPosition
      )
      setPropertyName("localPosition")
      setEvaluator(Vector3Evaluator())
      interpolator = AccelerateDecelerateInterpolator()
      start()
    }.doWhenFinish { doWhenFinish() }
  }

  /**
   * Rotates a target node to a desired position.
   *
   * @param anchorNode A target [Node] for translating.
   * @param durationTime A duration time for executing this animation.
   * @param doWhenFinish A lambda for executing after finish this animation.
   */
  inline fun rotateModel(
    anchorNode: Node,
    durationTime: Long = 150L,
    crossinline doWhenFinish: () -> Unit = {}
  ) {
    ObjectAnimator().apply {
      setAutoCancel(false)
      target = anchorNode
      duration = durationTime
      setObjectValues(
        Quaternion.axisAngle(Vector3(0.0f, 0.0f, 0.0f), 0.0f),
        Quaternion.axisAngle(Vector3(2.0f, 2.0f, 2.0f), 2360f)
      )
      setPropertyName("localRotation")
      setEvaluator(QuaternionEvaluator())
      interpolator = AccelerateDecelerateInterpolator()
      start()
    }.doWhenFinish { doWhenFinish() }
  }
}

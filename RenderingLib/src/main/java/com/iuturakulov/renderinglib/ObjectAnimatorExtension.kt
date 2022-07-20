package com.iuturakulov.renderinglib

import android.animation.Animator
import android.animation.ObjectAnimator

/**
 * An [ObjectAnimator] extension for executing a lambda after finish the animation.
 *
 * @param block A lambda for executing after finish the animation.
 */
inline fun ObjectAnimator.doWhenFinish(
  crossinline block: () -> Unit
) {
  addListener(object : Animator.AnimatorListener {
    override fun onAnimationEnd(animation: Animator?) = block()
    override fun onAnimationStart(animation: Animator?) = Unit
    override fun onAnimationCancel(animation: Animator?) = Unit
    override fun onAnimationRepeat(animation: Animator?) = Unit
  })
}

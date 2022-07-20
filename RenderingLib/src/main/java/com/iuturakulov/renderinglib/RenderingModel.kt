package com.iuturakulov.renderinglib

import com.google.ar.sceneform.math.Vector3

/**
 * A rendering model that can be drawn on the AR frame.
 *
 * @param name A name of the model.
 * @param model A rendering source.
 * @param direction A default direction of the model when initializing.
 * @param scale A default scale of the model when initializing.
 * @param localPosition A default local of the model when initializing.
 */
data class RenderingModel(
  val name: String,
  val model: String,
  val direction: Vector3 = Vector3(0f, 0f, 1f),
  val scale: Float = 1f,
  val localPosition: Vector3 = Vector3(0.5f, 0.5f, 0.5f)
)

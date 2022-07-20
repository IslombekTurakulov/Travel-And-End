package com.iuturakulov.renderinglib

import com.google.ar.sceneform.math.Vector3
import kotlin.random.Random

object ArchitectureModels {

  private fun getRegistan() = RenderingModel(
    name = "registan",
    model = "registan.sfb",
    localPosition = DEFAULT_POSITION_ARCHITECTURE
  )

  private fun getAbra() = RenderingModel(
    name = "abra",
    model = "abra.sfb",
    scale = 2.0f,
    localPosition = DEFAULT_POSITION_ARCHITECTURE
  )


  private fun getSquirtle() = RenderingModel(
    name = "squirtle",
    model = "squirtle.sfb",
    direction = Vector3(0.75f, 0f, 1f),
    scale = 2.5f,
    localPosition = DEFAULT_POSITION_ARCHITECTURE
  )


  fun getRandomPokemon(): RenderingModel {
    return when (Random.nextInt(13)) {
      0 -> getAbra()
      11 -> getSquirtle()
      else -> getAbra()
    }
  }

  fun getPokemonByName(name: String): RenderingModel {
    return when (name) {
      "abra" -> getAbra()
      "squirtle" -> getSquirtle()
      else -> getAbra()
    }
  }

  private val DEFAULT_POSITION_ARCHITECTURE = Vector3(0f, -0.25f, -3f)

  val DEFAULT_POSITION_DETAILS_ARCHITECTURE = Vector3(0f, -0.88f, -2f)
}

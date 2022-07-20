package com.iuturakulov.uzbarchitecture_ar.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun <T : Fragment> AppCompatActivity.findFragmentAs(@IdRes resource: Int): T {
  return supportFragmentManager.findFragmentById(resource) as? T
    ?: throw IllegalArgumentException("No ID resource -> $resource")
}

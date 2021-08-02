package com.player.androidcore.ext

import androidx.fragment.app.Fragment

fun Fragment.getDeviceOrientation() = requireActivity().getDeviceOrientation()

fun Fragment.isPortrait() = requireActivity().isPortrait()

fun Fragment.isLandscape() = requireActivity().isLandscape()

fun Fragment.showSystemUI() = requireActivity().showSystemUI()

fun Fragment.hideSystemUI() = requireActivity().hideSystemUI()

fun Fragment.rotateScreenToLandscape() = requireActivity().rotateScreenToLandscape()

fun Fragment.rotateScreenToPortrait() = requireActivity().rotateScreenToPortrait()
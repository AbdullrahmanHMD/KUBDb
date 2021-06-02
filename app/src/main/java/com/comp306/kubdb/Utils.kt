package com.comp306.kubdb

import kotlin.math.pow
import kotlin.math.roundToInt

fun Float.precisionTo(precision: Int) = ((this * 10.0.pow(precision)).roundToInt() / 10.0).toFloat()

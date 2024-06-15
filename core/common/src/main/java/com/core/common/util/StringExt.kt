package com.core.common.util

val String.Companion.Empty
    get() = ""

fun String?.getRouteName() = this?.takeLastWhile { it != '.' }
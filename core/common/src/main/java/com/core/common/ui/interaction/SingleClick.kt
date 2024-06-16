package com.core.common.ui.interaction

fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = System.currentTimeMillis()
        if (now - latest >= 1000) {
            onClick()
            latest = now
        }
    }
}
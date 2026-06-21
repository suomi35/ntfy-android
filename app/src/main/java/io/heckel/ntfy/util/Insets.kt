package io.heckel.ntfy.util

import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun AppCompatActivity.setupEdgeToEdge() {
    enableEdgeToEdge()
}

/** Top inset for edge-to-edge with an AppCompat action bar (status bar only; action bar layout is separate). */
fun View.applyStatusBarTopPadding() {
    applySystemBarPadding(left = false, top = true, right = false, bottom = false)
}

fun View.applySystemBarPadding(
    left: Boolean = true,
    top: Boolean = true,
    right: Boolean = true,
    bottom: Boolean = true,
) {
    val initialPadding = Insets.of(paddingLeft, paddingTop, paddingRight, paddingBottom)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(
            left = initialPadding.left + if (left) systemBars.left else 0,
            top = initialPadding.top + if (top) systemBars.top else 0,
            right = initialPadding.right + if (right) systemBars.right else 0,
            bottom = initialPadding.bottom + if (bottom) systemBars.bottom else 0,
        )
        windowInsets
    }
    ViewCompat.requestApplyInsets(this)
}

fun View.applySystemBarMargin(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false,
) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    val initialMargin = Insets.of(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        val marginParams = view.layoutParams as ViewGroup.MarginLayoutParams
        marginParams.leftMargin = initialMargin.left + if (left) systemBars.left else 0
        marginParams.topMargin = initialMargin.top + if (top) systemBars.top else 0
        marginParams.rightMargin = initialMargin.right + if (right) systemBars.right else 0
        marginParams.bottomMargin = initialMargin.bottom + if (bottom) systemBars.bottom else 0
        view.layoutParams = marginParams
        windowInsets
    }
    ViewCompat.requestApplyInsets(this)
}

package com.example.tickit.ui.carousels

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CarouselLayoutManager(context: Context, scaleOnCenter: Boolean) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        if (childCount == 0) return
        scaleChildren()
    }
    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleChildren()
        return scrolled
    }

    private fun scaleChildren() {

        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceToCenter = abs(centerX - childCenterX)
            var scale = 0f

            if (distanceToCenter < centerX) {
                scale = 1.0f - (distanceToCenter / centerX.toFloat() * 2f)
            } else {
                scale = 0.8f
            }

            child.scaleX = scale.coerceIn(0.8f, 1.0f)
            child.scaleY = scale.coerceIn(0.8f, 1.0f)

            child.isClickable = distanceToCenter >= centerX
        }
    }
}
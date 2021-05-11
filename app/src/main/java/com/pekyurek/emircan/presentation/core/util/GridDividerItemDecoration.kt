package com.pekyurek.emircan.presentation.core.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class GridDividerItemDecoration(
    private val spacingDp : Float,
    private val spanCount : Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val bit = if (spacingDp > spanCount) (spacingDp / spanCount).roundToInt() else 1
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition

        outRect.top = if (itemPosition < spanCount) 0 else  bit * spanCount
        outRect.bottom = 0

        val rowPosition = itemPosition % spanCount
        outRect.left = rowPosition * bit
        outRect.right = (spanCount - rowPosition - 1) * bit
    }
}
package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

class ImageListLayoutManager : RecyclerView.LayoutManager() {
    private val cell: Int
        get() = width / 5

    override fun canScrollVertically() = true

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT,
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)
        if (state.itemCount <= 0) return
        fillDown(recycler)
    }

    private fun fillDown(recycler: RecyclerView.Recycler) {
        val rect = Rect(0, 0, cell * 5, cell * 4)
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            locateView(rect, view, i)
            if (i % 6 == 5) rect.offset(0, cell * 4)
        }
    }

    private fun measureChild(child: View, koef: Int) {
        measureChildWithMargins(child, 0, 0)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(cell * koef, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(cell * koef, View.MeasureSpec.EXACTLY)
        measureChildWithDecorationsAndMargin(child, widthSpec, heightSpec)
    }

    private fun measureChildWithDecorationsAndMargin(child: View, widthSpec: Int, heightSpec: Int) {
        val decorRect = Rect()
        calculateItemDecorationsForChild(child, decorRect)
        val lp = child.layoutParams as RecyclerView.LayoutParams
        val width = updateSpecWithExtra(
            widthSpec,
            lp.leftMargin + decorRect.left,
            lp.rightMargin + decorRect.right
        )
        val height = updateSpecWithExtra(
            heightSpec,
            lp.topMargin + decorRect.top,
            lp.bottomMargin + decorRect.bottom
        )
        child.measure(width, height)
    }

    private fun updateSpecWithExtra(spec: Int, startInset: Int, endInset: Int): Int {
        if (startInset == 0 && endInset == 0) return spec
        val mode = View.MeasureSpec.getMode(spec)
        return if (mode == View.MeasureSpec.AT_MOST || mode == View.MeasureSpec.EXACTLY) {
            View.MeasureSpec.makeMeasureSpec(
                View.MeasureSpec.getSize(spec) - startInset - endInset,
                mode
            )
        } else {
            spec
        }
    }

    private fun locateView(r: Rect, v: View, i: Int) = when (i % 6) {
        0 -> {
            measureChild(v, 3)
            layoutDecorated(v, r.left, r.top, r.left + cell * 3, r.top + cell * 3)
        }
        1 -> {
            measureChild(v, 2)
            layoutDecorated(v, r.left + cell * 3, r.top, r.right, r.top + cell * 2)
        }
        2 -> {
            measureChild(v, 1)
            layoutDecorated(v, r.left, r.top + cell * 3, r.left + cell, r.bottom)
        }
        3 -> {
            measureChild(v, 1)
            layoutDecorated(v, r.left + cell, r.top + cell * 3, r.left + cell * 2, r.bottom)
        }
        4 -> {
            measureChild(v, 1)
            layoutDecorated(v, r.left + cell * 2, r.top + cell * 3, r.left + cell * 3, r.bottom)
        }
        5 -> {
            measureChild(v, 2)
            layoutDecorated(v, r.left + cell * 3, r.top + cell * 2, r.right, r.bottom)
        }
        else -> error("Item is out of range")
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val delta = scrollVerticallyInternal(dy)
        offsetChildrenVertical(-delta)
        return delta
    }

    private fun scrollVerticallyInternal(dy: Int): Int {
        val childCount = childCount
        if (childCount == 0) {
            return 0
        }

        val topView = getChildAt(0) ?: error("Can't get first child")
        val bottomView = getChildAt(childCount - 1) ?: error("Can't get last child")

        val viewSpan = getDecoratedBottom(bottomView) - getDecoratedTop(topView)
        if (viewSpan <= height) {
            if (getPosition(bottomView) % 6 == 1) {
                getChildAt(childCount - 2)?.let {
                    if (getDecoratedBottom(it) - getDecoratedTop(topView) <= height) {
                        return 0
                    }
                }
            } else {
                return 0
            }
        }

        return if (dy < 0) {
            val firstViewAdapterPos = getPosition(topView)
            if (firstViewAdapterPos > 0) dy else max(getDecoratedTop(topView), dy)
        } else {
            val lastViewAdapterPos = getPosition(bottomView)
            if (lastViewAdapterPos < itemCount - 1) {
                dy
            } else {
                val offset = if (getPosition(bottomView) % 6 == 1) cell else 0
                min(getDecoratedBottom(bottomView) - height + offset, dy)
            }
        }
    }
}
package com.turina1v.moviesfilter.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(var columnNumber: Int, var spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val column: Int = position % columnNumber
        outRect.left = spacing - column * spacing / columnNumber
        outRect.right = (column + 1) * spacing / columnNumber

        if (position < columnNumber) outRect.top = spacing // top edge
        outRect.bottom = spacing // item bottom
    }
}

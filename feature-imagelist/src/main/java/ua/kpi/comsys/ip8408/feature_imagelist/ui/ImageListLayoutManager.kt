package ua.kpi.comsys.ip8408.feature_imagelist.ui

import androidx.recyclerview.widget.RecyclerView

class ImageListLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT,
        )
    }

//    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
//        recycler?.let {
//            val view = it.getViewForPosition(0)
//            addView(view)
//            measureChildWithMargins(view, 0, 0)
//            layoutDecorated(view, 0, 0, width, height)
//        }
//    }

    fun measure() {
        
    }
}
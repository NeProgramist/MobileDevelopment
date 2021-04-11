package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8408.core_ui.utils.dp
import ua.kpi.comsys.ip8408.feature_filmlist.R
import kotlin.math.abs
import kotlin.math.max

class DeleteCallback(
    private val context: Context,
    private val adapter: FilmListAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val icon = ContextCompat.getDrawable(context, R.drawable.ic_trash)
    private val iconPadding = context.resources.getDimension(R.dimen.recycler_item_padding)
    private val iconMargin = context.resources.getDimension(R.dimen.recycler_item_margin)
    private val iconWidth = icon?.intrinsicWidth ?: 0
    private val iconHeight = icon?.intrinsicHeight ?: 0


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapter.removeItem(position)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val view = viewHolder.itemView
        val width = view.width
        val height = view.height

        if (dX < 0) {
            val center = max(width - abs(dX) / 2.0, width / 2.0) + iconMargin + iconPadding
            val iconTop = view.top + (height - iconHeight) / 2
            val iconLeft = center - iconWidth / 2
            val iconRight = center + iconWidth / 2
            val iconBottom = iconTop + iconHeight

            icon?.setBounds(iconLeft.toInt(), iconTop, iconRight.toInt(), iconBottom)
            icon?.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }
}
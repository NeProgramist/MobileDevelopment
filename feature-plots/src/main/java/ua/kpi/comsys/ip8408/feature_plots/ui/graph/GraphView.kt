package ua.kpi.comsys.ip8408.feature_plots.ui.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ua.kpi.comsys.ip8408.feature_plots.models.Graph
import ua.kpi.comsys.ip8408.feature_plots.models.Point

class GraphView : View {
    private val graph by lazy {
        Graph(width.toFloat(), height.toFloat())
    }
    private val plotDrawer = Paint().also {
        it.color = Color.BLUE
        it.strokeWidth = 3f
        it.style = Paint.Style.FILL
    }
    private val graphPaint = Paint().also {
        it.color = Color.BLACK
        it.strokeWidth = 3f
        it.style = Paint.Style.FILL
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    var function: ((Float) -> Float)? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawGraph(canvas)

        val points = mutableListOf<Pair<Float, Float>>()
        for (i in -200..200) {
            val x = graph.xToGraph(i / 50f)
            function?.let {
                val y = graph.yToGraph(it(i / 50f))
                points.add(x to y)
            }
        }

        canvas?.let {
            for (ind in 0 until points.size - 1) {
                it.drawLine(
                    points[ind].first,
                    points[ind].second,
                    points[ind + 1].first,
                    points[ind + 1].second,
                    plotDrawer
                )
            }
        }
    }

    private fun drawGraph(canvas: Canvas?) {
        canvas?.let {
            with(graph) {
                it.drawLine(top, bottom, graphPaint) // x-axis
                it.drawLine(left, right, graphPaint) // y-axis

                if (arrows) {
                    it.drawLine(top, top + Point(-15f, 30f), graphPaint) // arrow y
                    it.drawLine(top, top + Point(15f, 30f), graphPaint)

                    it.drawLine(right, right - Point(30f, 15f), graphPaint) // arrow x
                    it.drawLine(right, right - Point(30f, -15f), graphPaint)
                }

                val unitX = center + Point(unit, 0f)
                val unitY = center - Point(0f, unit)

                it.drawLine(unitX + Point(0f, 15f), unitX + Point(0f, -15f), graphPaint)
                it.drawLine(unitY + Point(15f, 0f), unitY + Point(-15f, 0f), graphPaint)
            }
        }
    }

    private fun Canvas.drawLine(pointFrom: Point, pointTo: Point, paint: Paint) {
        drawLine(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y, paint)
    }
}
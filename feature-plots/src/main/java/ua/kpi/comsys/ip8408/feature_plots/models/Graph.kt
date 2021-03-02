package ua.kpi.comsys.ip8408.feature_plots.models

import kotlin.math.min

data class Graph(
    var width: Float,
    var height: Float,
    val arrows: Boolean = true,
) {
    val left = Point(0f, height / 2f)
    val right = Point(width, height / 2f)
    val top = Point(width / 2f, 0f)
    val bottom = Point(width / 2f, height)
    val center = Point(width / 2f, height / 2f)
    val unit = min(width, height) / 8f

    fun xToGraph(x: Float) = center.x + x * unit
    fun yToGraph(y: Float) = center.y - y * unit
}

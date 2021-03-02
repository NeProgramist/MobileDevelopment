package ua.kpi.comsys.ip8408.feature_plots.models

data class Point(val x: Float, val y: Float) {
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun div(divider: Float) = Point(x / divider, y / divider)
}

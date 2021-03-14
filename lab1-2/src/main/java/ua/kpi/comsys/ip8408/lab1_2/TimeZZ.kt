package ua.kpi.comsys.ip8408.lab1_2

import java.time.LocalDateTime

/*
    Grade book number = 8408
    Variant = 8408 % 2 + 1 = 1

    Create class TimeXY where X - first letter of name; Y - second letter of surname
    Name - Zhenya Zasko, so class name TimeZZ
 */

/*
    Unit is experimental in kotlin, so I use Int
 */
class TimeZZ(
    private val hours: Int = 0,
    private val minutes: Int = 0,
    private val seconds: Int = 0,
) {
    constructor(date: LocalDateTime) : this(
        date.hour,
        date.minute,
        date.second,
    )

    init {
        require(
            hours in 0 until maxHours &&
            minutes in 0 until maxMinutes &&
            seconds in 0 until maxSeconds
        )
    }

    fun timeToString(): String {
        val fixCount: (String) -> String = { if (it.length == 1) "0$it" else it }
        val midTime = maxHours / 2

        val (hrs, ZZ) = if (hours >= midTime) "${hours % midTime}" to "PM" else "$hours" to "AM"
        val hh = fixCount(if (hrs == "0") "12" else hrs)
        val mm = fixCount(minutes.toString())
        val ss = fixCount(seconds.toString())

        return "$hh:$mm:$ss $ZZ"
    }

    fun plus(other: TimeZZ) = plus(this, other)
    fun minus(other: TimeZZ) = minus(this, other)

    override fun toString() = timeToString()

    companion object {
        fun plus(first: TimeZZ, second: TimeZZ): TimeZZ {
            val ss = first.seconds + second.seconds
            val mm = first.minutes + second.minutes + ss / maxSeconds
            val hh = first.hours + second.hours + mm / maxMinutes

            return TimeZZ(hh % maxHours, mm % maxMinutes, ss % maxMinutes)
        }

        fun minus(first: TimeZZ, second: TimeZZ): TimeZZ {
            val minusSec = first.seconds - second.seconds
            val ss = if (minusSec >= 0) minusSec else 2 * maxSeconds + minusSec

            val minusMin = first.minutes - second.minutes - ss / maxSeconds
            val mm = if (minusMin >= 0) minusMin else 2 * maxMinutes + minusMin

            val minusHour = first.hours - second.hours - mm / maxMinutes
            val hh = if (minusHour >= 0) minusHour else maxHours + minusHour

            return TimeZZ(hh, mm % maxMinutes, ss % maxSeconds)
        }

        // in order to get rid of "magic numbers"
        const val maxSeconds = 60
        const val maxMinutes = 60
        const val maxHours = 24
    }
}

// Usage

fun main() {
    println("-------------------------------")

    val empty = TimeZZ()
    val common = TimeZZ(2, 14, 51)
//    val error = TimeZZ(99, 14, 51)
    val date = TimeZZ(LocalDateTime.now())

    println("-------------------------------")

    println(empty.timeToString())
    println(common.timeToString())
    println(date.timeToString())

    println("-------------------------------")

    println("""
        first: $common + second: $date
        result: ${common.plus(date)}
        """.trimIndent()
    )

    println("""
        first: $common - second: $date
        result: ${common.minus(date)}
        """.trimIndent()
    )

    println("-------------------------------")

    println("""
        first: $common + second: $date
        result: ${TimeZZ.plus(date, common)}
        """.trimIndent()
    )

    println("""
        first: $common - second: $date
        result: ${TimeZZ.minus(date, common)}
        """.trimIndent()
    )

    println("-------------------------------")

    // test plus/minus
    val time1 = TimeZZ(23, 59, 59)
    val time2 = TimeZZ(12, 0, 1)
    val time3 = TimeZZ(0, 0, 0)
    val time4 = TimeZZ(0, 0, 1)

    println("""
        first: $time1 + second: $time2
        result: ${time1.plus(time2)}
        """.trimIndent()
    )

    println("""
        first: $time3 - second: $time4
        result: ${time3.minus(time4)}
        """.trimIndent()
    )

    println("-------------------------------")

    // fix mistakes
    println(TimeZZ(0, 0, 0))
    println(TimeZZ(12, 0, 0))
}

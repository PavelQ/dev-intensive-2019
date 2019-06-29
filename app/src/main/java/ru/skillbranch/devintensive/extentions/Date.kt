package ru.skillbranch.devintensive.extentions

import java.text.SimpleDateFormat
import java.util.*

const val RU_LOCALE = "ru"

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale(RU_LOCALE))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}


fun Date.humanizeDiff(date: Date = this): String {
    val time = date.time
    val currentTime = Date().time
    var difTime = currentTime - time

    if (difTime < 0) {
        difTime *= -1
        return when (difTime) {
            in 0..SECOND -> "сейчас будет"
            in SECOND..45 * SECOND -> "через несколько"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> "через ${wordFormCorrecter(
                difTime / MINUTE,
                TimeUnits.MINUTE
            )}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> "через ${wordFormCorrecter(
                difTime / HOUR,
                TimeUnits.HOUR
            )}"
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> "через ${wordFormCorrecter(
                difTime / DAY,
                TimeUnits.DAY
            )}"
            else -> "более чем через год"
        }
    }
    return when (difTime) {
        in 0..SECOND -> "только что"
        in SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> "${wordFormCorrecter(
            difTime / MINUTE,
            TimeUnits.MINUTE
        )} назад"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> "${wordFormCorrecter(
            difTime / HOUR,
            TimeUnits.HOUR
        )} назад"
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> "${wordFormCorrecter(
            difTime / DAY,
            TimeUnits.DAY
        )} назад"
        else -> "более года назад"
    }
}

fun wordFormCorrecter(count: Long, timeUnit: TimeUnits): String {
    //10-19
    if (count / 10L == 1L) {
        return "$count " +
                when (timeUnit) {
                    TimeUnits.MINUTE -> "минут"
                    TimeUnits.HOUR -> "часов"
                    TimeUnits.DAY -> "дней"
                    else -> throw IllegalArgumentException("wrong count : $count, unit: $timeUnit")
                }
    }

    return "$count " +
            when (count % 10) {
                1L -> when (timeUnit) {
                    TimeUnits.MINUTE -> "минуту"
                    TimeUnits.HOUR -> "час"
                    TimeUnits.DAY -> "день"
                    else -> IllegalArgumentException("wrong count : $count, unit: $timeUnit")
                }
                in 2..4 -> when (timeUnit) {
                    TimeUnits.MINUTE -> "минуты"
                    TimeUnits.HOUR -> "часа"
                    TimeUnits.DAY -> "дня"
                    else -> IllegalArgumentException("wrong count : $count, unit: $timeUnit")
                }
                in 5..9, 0L -> when (timeUnit) {
                    TimeUnits.MINUTE -> "минут"
                    TimeUnits.HOUR -> "часов"
                    TimeUnits.DAY -> "дней"
                    else -> IllegalArgumentException("wrong count : $count, unit: $timeUnit")
                }
                else -> IllegalArgumentException("wrong count : $count, unit: $timeUnit")

            }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
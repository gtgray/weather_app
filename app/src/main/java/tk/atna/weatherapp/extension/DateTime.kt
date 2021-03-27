package tk.atna.weatherapp.extension

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

fun Long.toSimpleTime(): String {
    return toUTCTime().format(DateTimeFormat.SIMPLE_TIME_FORMAT)
}

fun Long.toUTCTime(): LocalTime {
    return toUTCZonedDateTime().toLocalTime()
}

fun Long.toUTCZonedDateTime(): ZonedDateTime {
    return ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneOffset.UTC
    )
}

object DateTimeFormat {
    val SIMPLE_TIME_FORMAT: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendValue(ChronoField.HOUR_OF_DAY, 2)
        .appendLiteral(':')
        .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
        .toFormatter()
}
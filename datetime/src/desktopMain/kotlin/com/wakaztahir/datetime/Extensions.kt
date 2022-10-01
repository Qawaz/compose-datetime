package com.wakaztahir.datetime

import java.time.*
import java.util.*

internal val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(this.year, this.month)

internal val LocalTime.isAM: Boolean
    get() = this.hour in 0..11

internal val LocalTime.simpleHour: Int
    get() {
        val tempHour = this.hour % 12
        return if (tempHour == 0) 12 else tempHour
    }

internal fun Month.getShortLocalName(locale: Locale): String =
    this.getDisplayName(java.time.format.TextStyle.SHORT, locale)

internal fun Month.getFullLocalName(locale: Locale) =
    this.getDisplayName(java.time.format.TextStyle.FULL, locale)

internal fun DayOfWeek.getShortLocalName(locale: Locale) =
    this.getDisplayName(java.time.format.TextStyle.SHORT, locale)

internal fun LocalTime.toAM(): LocalTime = if (this.isAM) this else this.minusHours(12)
internal fun LocalTime.toPM(): LocalTime = if (!this.isAM) this else this.plusHours(12)

internal fun LocalTime.noSeconds(): LocalTime = LocalTime.of(this.hour, this.minute)

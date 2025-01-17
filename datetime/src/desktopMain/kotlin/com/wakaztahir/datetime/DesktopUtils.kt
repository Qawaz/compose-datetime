package com.wakaztahir.datetime

import androidx.compose.runtime.Composable
import kotlinx.datetime.toJavaLocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
internal actual fun isSmallDevice(): Boolean {
    return false
}

internal actual fun kotlinx.datetime.LocalDate.getNumDays(): Int {
    return toJavaLocalDate().let { it.month.length(it.isLeapYear) }
}

internal actual fun kotlinx.datetime.LocalDate.getMonthShortLocalName(): String {
    return toJavaLocalDate().month.getDisplayName(TextStyle.SHORT, (Locale.getDefault()))
}

internal actual fun kotlinx.datetime.LocalDate.getDayOfWeekShortLocalName(): String {
    return toJavaLocalDate().dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
}
internal actual fun kotlinx.datetime.LocalDate.getMonthDisplayName(): String {
    return toJavaLocalDate().month.getDisplayName(TextStyle.FULL, Locale.getDefault())
}
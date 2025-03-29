package com.example.beatfranticallyidle.utils.numberformatter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

fun formatBigIntegerNumber(value: BigInteger): String {

    if (value < BigInteger.valueOf(1000)) {
        return value.toString()
    }

    val units = arrayOf(
        "", "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp",
        "Oc", "No", "De", "UnD", "DuD", "TrD", "QuD",
        "QuiD", "SeD", "SpD", "OcD", "NoD", "Vi"
    )
    val exponent = value.toString().length - 1
    val unitIndex = exponent / 3

    return if (unitIndex >= units.size) {
        "%.0e".format(BigDecimal(value))
    } else {
        val divisor = BigInteger.TEN.pow(unitIndex * 3)
        val formattedValue = BigDecimal(value).divide(
            BigDecimal(divisor), 2, RoundingMode.HALF_UP
        )
        "${formattedValue.intValueExact()}${units[unitIndex]}"
    }
}

fun formatBigDecimalNumber(value: BigDecimal): String {

    if (value < BigDecimal.valueOf(1000)) {
        return value.toInt().toString()
    }

    val units = arrayOf(
        "", "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp",
        "Oc", "No", "De", "UnD", "DuD", "TrD", "QuD",
        "QuiD", "SeD", "SpD", "OcD", "NoD", "Vi"
    )
    val exponent = value.precision() - value.scale() - 1
    val unitIndex = exponent / 3

    return if (unitIndex >= units.size) {
        "%.0e".format(value)
    } else {
        val divisor = BigDecimal.TEN.pow(unitIndex * 3)
        val formattedValue = value.divide(
            divisor, 2, RoundingMode.HALF_UP
        )
        "${formattedValue.intValueExact()}${units[unitIndex]}"
    }
}
package ru.shvets.springcalculator.model

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 08:13
 */

data class Calculator(
    val expr: String? = null,
    var result: Double? = null,
)
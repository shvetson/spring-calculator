package ru.shvets.springcalculator.model

import ru.shvets.springcalculator.exception.DivideByZeroException
import kotlin.math.roundToInt

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  06.03.2023 22:11
 */

class Calc(expr: String) {
    private val elements = expr.split(" ")
    private var pos = 0

    fun calculate(): Double {
        var result: Double = multiply()

        while (pos < elements.size) {
            val operator: String = elements[pos++]
            val next = multiply()
            val action = selectAction(operator)
            result = action(result, next)
        }
        return (result * 100.0).roundToInt() / 100.0
    }

    private fun multiply(): Double {
        var result: Double = elements[pos++].toDouble()

        while (pos < elements.size) {
            val operator: String = elements[pos]

            if (operator != "*" && operator != "/") {
                break
            } else {
                pos++
            }

            val next = elements[pos++].toDouble()
            checkDivideByZero(operator, next)
            val action = selectAction(operator)
            result = action(result, next)
        }
        return result
    }

    private fun selectAction(operator: String): (Double, Double) -> Double {
        return when (operator) {
            "+" -> { x, y -> x + y }
            "-" -> { x, y -> x - y }
            "*" -> { x, y -> x * y }
            "/" -> { x, y -> x / y }
            else -> { x, y -> 0.0 }
        }
    }

    private fun checkDivideByZero(operator: String, number: Double) {
        if (operator.trim() == "/" && number == 0.0) {
            throw DivideByZeroException()
        }
    }
}
package ru.shvets.springcalculator.model

import ru.shvets.springcalculator.exception.DivideByZeroException
import ru.shvets.springcalculator.exception.UnexpectedElementException
import kotlin.math.roundToInt

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  06.03.2023 22:11
 */

// метод рекурсивного спуска - проверка на скобки, потом * и / и в конце на + и -
class Calc(expr: String) {
    private val elements = expr.split(" ")
    private var pos = 0

    fun calculate(): Double {
        var result: Double = multiply()

        while (pos < elements.size) {
            val operator: String = elements[pos]

            // эта проверка необходима только при работе со скобками
            if (operator != "+" && operator != "-") {
                break
            } else {
                pos++
            }

            val next = multiply()
            val action = selectAction(operator)
            result = action(result, next)
        }
        return (result * 100.0).roundToInt() / 100.0 // округляем до сотых
    }

    // calculate -> multiply -> calculate
    private fun multiply(): Double {
        var result = factor()

        while (pos < elements.size) {
            val operator: String = elements[pos]

            if (operator != "*" && operator != "/") {
                break
            } else {
                pos++
            }

            val next = factor()
            checkDivideByZero(operator, next)
            val action = selectAction(operator)
            result = action(result, next)
        }
        return result
    }

    // calculate -> multiply -> factor -> calculate
    private fun factor(): Double {
        val current = elements[pos]
        val result: Double

        if (current == "(") {
            pos++
            result = calculate()
            val closingBracket: String

            if (pos < elements.size) {
                closingBracket = elements[pos]
            } else {
                throw UnexpectedElementException("Неожидаемый конец выражения (endOf)")
            }

            if (closingBracket == ")") {
                pos++
                return result
            }
            throw UnexpectedElementException("Вместо закрывающей скобки был определен следующий символ $closingBracket")
        }

        pos++
        return current.toDouble()
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
package ru.shvets.springcalculator.model

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 09:36
 */

class Parser(_expr: String) {
    private val expr = _expr

    fun parse(): String {
        val posOperators = mutableListOf<Int>()
//        val pattern: Pattern = Pattern.compile("\\s*(\\s|\\+|-|\\*|/|\\(|\\))\\s*")
        val pattern: Pattern = Pattern.compile("[\\+|\\-|\\*|/|\\(|\\)]")

        val expression = expr.replace("\\s*".toRegex(), "")
        val arrayNumbers = expression.split(pattern)

        val matcher: Matcher = pattern.matcher(expression);
        while (matcher.find()) {
            posOperators.add(matcher.start())
        }

        val sb = StringBuilder()
        posOperators.forEachIndexed { index, pos ->
            // число
            if (arrayNumbers[index] == "") {
                sb.append(arrayNumbers[index])
            } else {
                sb.append("${arrayNumbers[index]} ")
            }

            // арифметическое действие или скобки
            sb.append("${expression.substring(pos, pos + 1)} ")

            if (index == posOperators.size - 1) {
                sb.append(arrayNumbers[index + 1])
            }
        }
        return sb.toString()
    }
}
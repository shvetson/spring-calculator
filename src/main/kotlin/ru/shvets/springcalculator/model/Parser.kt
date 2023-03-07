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
        val pattern: Pattern = Pattern.compile("\\s*(\\s|\\+|-|\\*|/)\\s*")

        val expression = expr.replace("\\s*".toRegex(), "")
        val arrayNumbers = expression.split(pattern)

        val matcher: Matcher = pattern.matcher(expression);
        while (matcher.find()) {
            posOperators.add(matcher.start())
        }

        val sb = StringBuilder()
        posOperators.forEachIndexed { index, pos ->
            sb.append("${arrayNumbers[index]} ${expression.substring(pos, pos + 1)} ")
            if (index == posOperators.size - 1) {
                sb.append(arrayNumbers[index + 1])
            }
        }
        return sb.toString()
    }
}
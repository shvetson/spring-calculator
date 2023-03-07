package ru.shvets.springcalculator.service

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 08:46
 */

interface Calculation {
    fun parser(expr: String): String
    fun calculation(expr: String): Double
}
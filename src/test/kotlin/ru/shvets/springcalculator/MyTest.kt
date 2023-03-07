package ru.shvets.springcalculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.shvets.springcalculator.model.Calc
import ru.shvets.springcalculator.model.Parser

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 10:13
 */
class MyTest {

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given text with expression, When parsing, Then expect preparing expression to calculate`(){
        val str: String = " 9   * 2+ 3 - 6* 5+ 12 /  10 -9  / 9  * 1 "
        val parser = Parser(str)
        assertEquals("9 * 2 + 3 - 6 * 5 + 12 / 10 - 9 / 9 * 1", parser.parse())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression 5 plus 2 minus 1, When calculate, Then expect result 6,0`(){
        val expr: String = "5 + 2 - 1"
        val result = 6.0
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression 5 mul 4 div 3, When calculate, Then expect result 6,67`(){
        val expr: String = "5 * 4 / 3"
        val result = 6.67
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression 5 mul 4 plus 3 div 2 mul 4 minus 1, When calculate, Then expect result 25,0`(){
        val expr: String = "5 * 4 + 3 / 2 * 4 - 1"
        val result = 25.0
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }
}
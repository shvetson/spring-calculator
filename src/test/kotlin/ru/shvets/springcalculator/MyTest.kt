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
        val str = " 9   * 2+ 3 - 6* 5+ 12 /  10 -9  / 9  * 1 "
        val parser = Parser(str)
        assertEquals("9 * 2 + 3 - 6 * 5 + 12 / 10 - 9 / 9 * 1", parser.parse())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression only with plus and minus, When calculate, Then expect result 6,0`(){
        val expr = "5 + 2 - 1"
        val result = 6.0
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression only with multiplication and divide, When calculate, Then expect result 6,67`(){
        val expr = "5 * 4 / 3"
        val result = 6.67
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression with all arithmetic operations without brackets, When calculate, Then expect result 25,0`(){
        val expr = "5 * 4 + 3 / 2 * 4 - 1"
        val result = 25.0
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }

    @DisplayName("\uD83D\uDC4D")
    @Test
    fun `Given expression with all arithmetic operations wit brackets, When calculate, Then expect result 8,5`(){
        val expr = "1 + 5 - ( 3 - 2 ) * 2 + 3 * 3 / ( 7 - 5 )"
        val result = 8.5
        val calc = Calc(expr)
        assertEquals(result, calc.calculate())
    }
}
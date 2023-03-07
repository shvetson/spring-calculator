package ru.shvets.springcalculator.service

import org.springframework.stereotype.Service
import ru.shvets.springcalculator.model.Calc
import ru.shvets.springcalculator.model.Parser

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 08:36
 */

@Service
class CalcService: Calculation {

    override fun parser(expr: String): String {
        val parser = Parser(expr)
        return parser.parse()
    }

    override fun calculation(expr: String): Double {
        val calc = Calc(expr)
        return calc.calculate()
    }
}
package ru.shvets.springcalculator.controller

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.shvets.springcalculator.model.Calculator
import ru.shvets.springcalculator.service.CalcService
import ru.shvets.springcalculator.util.CalculatorValidator

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  06.03.2023 14:53
 */

@Controller
@RequestMapping("/api/v1")
class DefaultController(
    private val calcService: CalcService,
    private val calculatorValidator: CalculatorValidator,
) {
    @GetMapping
    fun showCalc(
        @ModelAttribute("calculator") calculator: Calculator,
    ): String {
        return "index"
    }

    @PostMapping
    fun calcExpr(
        @ModelAttribute("calculator") @Valid calculator: Calculator,
        bindingResult: BindingResult,
    ): String {

        calculatorValidator.validate(calculator, bindingResult)
        if (bindingResult.hasErrors()) {
            return "index"
        }

        val expression = calculator.expr?.let { calcService.parser(it) }
        if (expression != null) {
            calculator.result = calcService.calculation(expression)
        }
        return "index"
    }
}
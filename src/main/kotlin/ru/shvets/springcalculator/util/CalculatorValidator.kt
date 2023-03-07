package ru.shvets.springcalculator.util

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import ru.shvets.springcalculator.model.Calculator

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.03.2023 09:17
 */

@Component
class CalculatorValidator: Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return Calculator::javaClass == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val calculator = target as Calculator

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
            "expr",
            "field.expr.null",
            "Арифметическое выражение не должно быть пустым.")

        val min: Int = 3
        val max: Int = 50
        if (calculator.expr?.length!! !in min..max) {
            errors.rejectValue(
                "expr",
                "field.expr.range",
                "Длина арифметического выражения должна быть от $min до $max символов."
            )
        }
    }
}
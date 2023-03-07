package ru.shvets.springcalculator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCalculatorApplication

fun main(args: Array<String>) {
    runApplication<SpringCalculatorApplication>(*args)
}

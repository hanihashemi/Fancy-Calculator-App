package io.github.hanihashemi.fancycalculator

import androidx.compose.ui.graphics.Color
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonBlue
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonPink
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonYellow

sealed class ActionType(val symbol: String, val buttonColor: Color) {
data class Number(val number: Int) : ActionType(number.toString(), ButtonBlue)
    data class Operator(val operation: Operators) : ActionType(operation.symbol, ButtonPink)

    object Calculate : ActionType("=", ButtonYellow)
    object Delete : ActionType("⇽", ButtonBlue)
    object Clear : ActionType("AC", ButtonPink)
    object Decimal : ActionType(".", ButtonBlue)
    object Percentage: ActionType("%", ButtonPink)
}

sealed class Operators(val symbol: String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("x")
    object Divide : Operators("÷")
    object Power : Operators("^")
}

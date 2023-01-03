package io.github.hanihashemi.fancycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.hanihashemi.fancycalculator.ui.CalcButtonComponent
import io.github.hanihashemi.fancycalculator.ui.InputDisplayComponent
import io.github.hanihashemi.fancycalculator.ui.theme.FancyCalculatorTheme
import io.github.hanihashemi.fancycalculator.ui.theme.spacing

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FancyCalculatorTheme {
                val viewModel = viewModel<CalcViewModel>()
                val state =
                    viewModel.viewState.collectAsState(initial = CalcViewModel.ViewState("0")).value
                CalcScreen(state) {
                    viewModel.dispatch(it)
                }
            }
        }
    }
}

@Composable
private fun CalcScreen(state: CalcViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.lg, vertical = MaterialTheme.spacing.sm)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                InputDisplayComponent(state)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))
                CalcButtonsGridLayout(dispatcher)
            }
        }
    }
}

@Composable
private fun CalcButtonsGridLayout(dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        ActionType.Clear,
        ActionType.Operator(Operators.Power),
        ActionType.Percentage,
        ActionType.Operator(Operators.Divide),
        ActionType.Number(7),
        ActionType.Number(8),
        ActionType.Number(9),
        ActionType.Operator(Operators.Multiply),
        ActionType.Number(4),
        ActionType.Number(5),
        ActionType.Number(6),
        ActionType.Operator(Operators.Subtract),
        ActionType.Number(3),
        ActionType.Number(2),
        ActionType.Number(1),
        ActionType.Operator(Operators.Add),
        ActionType.Number(0),
        ActionType.Decimal,
        ActionType.Delete,
        ActionType.Calculate,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        content = {
            items(buttons) {
                CalcButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    color = it.buttonColor,
                    symbol = it.symbol
                ) {
                    dispatcher(it)
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun CalcScreenPreview() {
    FancyCalculatorTheme {
        CalcScreen(CalcViewModel.ViewState("0")) {}
    }
}
package io.github.hanihashemi.fancycalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonPink
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonShadowColorBottom
import io.github.hanihashemi.fancycalculator.ui.theme.ButtonShadowColorTop
import io.github.hanihashemi.fancycalculator.ui.theme.FancyCalculatorTheme

@Composable
internal fun CalcButtonComponent(
    modifier: Modifier = Modifier,
    color: Color,
    symbol: String,
    onClick: () -> Unit,
    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(fraction = 0.8f)
                .shadow(
                    color = ButtonShadowColorTop,
                    offsetX = (-4).dp,
                    offsetY = (-4).dp,
                    blurRadius = 8.dp,
                )
                .shadow(
                    color = ButtonShadowColorBottom,
                    offsetX = (4).dp,
                    offsetY = (4).dp,
                    blurRadius = 8.dp,
                )
                .clip(MaterialTheme.shapes.medium)
                .background(color)
        ) {
            Text(text = symbol)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalcButtonComponentPreview() {
    FancyCalculatorTheme {
        CalcButtonComponent(
            modifier = Modifier.size(100.dp),
            color = ButtonPink,
            symbol = "1",
            onClick = {},
        )
    }
}

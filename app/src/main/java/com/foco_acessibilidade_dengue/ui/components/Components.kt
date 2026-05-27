package com.foco_acessibilidade_dengue.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerticalMargin(margintTop: Dp, marginBottom: Dp, content: @Composable () -> Unit) {
    if (margintTop > 0.dp) Spacer(Modifier.height(margintTop))
    content()
    if (marginBottom > 0.dp) Spacer(Modifier.height(marginBottom))
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 32.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SubtitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize)
    }
}

@Composable
fun ParagraphText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = fontSize)
    }
}

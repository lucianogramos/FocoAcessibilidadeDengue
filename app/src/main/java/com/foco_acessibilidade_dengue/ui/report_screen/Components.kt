package com.foco_acessibilidade_dengue.ui.report_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foco_acessibilidade_dengue.R

@Composable
fun LocationCard() {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedCard (
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, colorScheme.outline),
        colors = CardDefaults.outlinedCardColors(containerColor = colorScheme.secondary)
    ) {
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.add_location_icon),
                    contentDescription = "Localização",
                    tint = colorScheme.onBackground
                )
                Spacer(Modifier.width(4.dp))
                Text("Localização", color = colorScheme.onBackground)
            }

            Text("Alterar", color = colorScheme.onBackground, textDecoration = TextDecoration.Underline)
        }

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(256.dp).padding(horizontal = 12.dp)
                .background(colorScheme.tertiary, RoundedCornerShape(8.dp))
                .border(BorderStroke(1.dp, colorScheme.outline), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Mapa do Google", color = colorScheme.onBackground)
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Bairro Alcides Junqueira",
            modifier = Modifier.padding(horizontal = 12.dp),
            color = colorScheme.onBackground.copy(alpha = 0.7f),
            fontSize = 14.sp
        )
        Text(
            text ="Ituiutaba - MG",
            modifier = Modifier.padding(horizontal = 12.dp),
            color = colorScheme.onBackground.copy(alpha = 0.7f),
            fontSize = 14.sp
        )
        Spacer(Modifier.height(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithCounter(
    value: String,
    onValueChange: (String) -> Unit,
    maxChar: Int,
    modifier: Modifier = Modifier,
    height: Dp,
    maxLines: Int = 1,
    placeholder: String = ""
) {
    val colorScheme = MaterialTheme.colorScheme
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) colorScheme.primary else colorScheme.outline
    val borderWidth = if (isFocused) 2.dp else 1.dp

    Column(
        modifier = modifier
            .fillMaxWidth().height(height)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(8.dp))
            .background(colorScheme.background, RoundedCornerShape(8.dp))
            .padding(bottom = 8.dp)
    ) {
        TextField(
            value = value,
            onValueChange = {
                if (it.length <= maxChar) onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth().weight(1f).onFocusChanged { isFocused = it.isFocused },
            maxLines = maxLines,
            placeholder = if (placeholder.isNotEmpty()) { { Text(placeholder) } } else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = colorScheme.onBackground.copy(alpha = 0.6f),
                unfocusedPlaceholderColor = colorScheme.onBackground.copy(alpha = 0.6f),
                focusedTextColor = colorScheme.onBackground,
                unfocusedTextColor = colorScheme.onBackground
            )
        )

        Text(
            text = "${value.length} / $maxChar",
            color = colorScheme.onBackground.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.End).padding(end = 12.dp)
        )
    }
}

@Preview
@Composable
fun TextFieldWithCounterPreview() {
    var text by remember { mutableStateOf("") }
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TextFieldWithCounter(
                value = text,
                onValueChange = { text = it },
                maxChar = 200,
                height = 128.dp,
                maxLines = 5,
                placeholder = "Digite algo..."
            )
        }
    }
}

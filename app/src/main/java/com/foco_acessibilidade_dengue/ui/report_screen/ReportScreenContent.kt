package com.foco_acessibilidade_dengue.ui.report_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.SubtitleText
import com.foco_acessibilidade_dengue.ui.components.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreenContent(modifier: Modifier = Modifier) {
    var categoryDropdownIsExpanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Opção 1") }
    var description by remember { mutableStateOf("") }
    val maxCharsOfDescription = 200

    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        val colorScheme = MaterialTheme.colorScheme

        TitleText("Novo reporte", marginTop = 4.dp)

        ParagraphText(
            text = "Envie fotos do problema para os orgãos públicos",
            marginTop = 8.dp,
            marginBottom = 12.dp
        )

        LocationCard()

        SubtitleText("Categoria do Problema", marginTop = 12.dp, marginBottom = 12.dp)

        ExposedDropdownMenuBox(
            expanded = categoryDropdownIsExpanded,
            onExpandedChange = { categoryDropdownIsExpanded = it }
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true, // Torna o campo imutável via teclado
                label = { Text("Selecione uma categoria", color = colorScheme.onBackground) },
                // O menuAnchor() liga fisicamente o menu a este campo de texto
                modifier = Modifier.fillMaxWidth().menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                    enabled = true
                ),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryDropdownIsExpanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.outline,
                    focusedTrailingIconColor = colorScheme.onBackground,
                    unfocusedTrailingIconColor = colorScheme.onBackground,
                    focusedLabelColor = colorScheme.onBackground,
                    unfocusedLabelColor = colorScheme.onBackground,
                    focusedContainerColor = colorScheme.background,
                    unfocusedContainerColor = colorScheme.background
                )
            )

            ExposedDropdownMenu(
                expanded = categoryDropdownIsExpanded,
                onDismissRequest = { categoryDropdownIsExpanded = false }, // Fecha se clicar fora
                containerColor = colorScheme.background,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, colorScheme.outline)
            ) {
                arrayOf("Dengue", "Acessibilidade").forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedCategory = option
                            categoryDropdownIsExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        SubtitleText("Descrição", marginTop = 12.dp, marginBottom = 12.dp)

        TextFieldWithCounter(
            value = description,
            onValueChange = { description = it },
            maxChar = maxCharsOfDescription,
            height = 128.dp,
            maxLines = 5,
            placeholder = "Descreva o problema encontrado..."
        )
    }
}

@Preview
@Composable
fun ReportScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ReportScreenContent()
        }
    }
}
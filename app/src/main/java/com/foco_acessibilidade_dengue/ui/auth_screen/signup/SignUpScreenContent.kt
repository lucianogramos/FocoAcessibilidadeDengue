package com.foco_acessibilidade_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foco_acessibilidade_dengue.data.remote.auth.cadastrarUsuario
import com.foco_acessibilidade_dengue.ui.auth_screen.AuthTextField
import com.foco_acessibilidade_dengue.ui.auth_screen.ClickHereLink
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.PrimaryButton
import com.foco_acessibilidade_dengue.ui.components.TitleText
import kotlinx.coroutines.launch

@Composable
fun SignUpScreenContent(modifier: Modifier = Modifier, toLoginScreen: (() -> Unit)? = null) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        TitleText(
            text = "Bem-vindo",
            marginTop = 4.dp
        )

        ParagraphText(
            text = "Faça seu cadastro para poder denunciar locais com foco de dengue e falta de acessibilidade",
            marginTop = 8.dp,
            marginBottom = 32.dp
        )

        AuthTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nome"
        )

        Spacer(Modifier.height(16.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@exemplo.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton("Cadastrar") {
            scope.launch {
                cadastrarUsuario(email, password)
            }
        }

        Spacer(Modifier.height(4.dp))

        ClickHereLink("Já tem uma conta? ", "navigation") { toLoginScreen?.invoke() }
    }
}

@Preview
@Composable
private fun SignUpScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUpScreenContent()
        }
    }
}

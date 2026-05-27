package com.foco_acessibilidade_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foco_acessibilidade_dengue.data.remote.auth.loginUsuario
import com.foco_acessibilidade_dengue.ui.auth_screen.AuthTextField
import com.foco_acessibilidade_dengue.ui.auth_screen.ClickHereLink
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.PrimaryButton
import com.foco_acessibilidade_dengue.ui.components.TitleText
import kotlinx.coroutines.launch

@Composable
fun LoginScreenContent(modifier: Modifier = Modifier, toSignUpScreen: (() -> Unit)? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        TitleText("Bem-vindo", marginTop = 4.dp)

        ParagraphText("Faça login para continuar", marginTop = 8.dp, marginBottom = 32.dp)

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
            visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton("Entrar")  {
            scope.launch {
                loginUsuario(email, password)
            }
        }

        Spacer(Modifier.height(4.dp))

        ClickHereLink("Ainda não tem uma conta? ", "navigation") { toSignUpScreen?.invoke() }
        ClickHereLink("Esqueceu a senha? ", "redirect") {}
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent()
        }
    }
}

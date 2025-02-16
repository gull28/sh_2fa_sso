package com.example.sh_2fa_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
import com.example.sh_2fa_app.ui.AppColors
import com.example.sh_2fa_app.ui.components.Base
import com.example.sh_2fa_app.ui.components.TotpGenerator

@Composable
fun Home(navController: NavController, prefsViewModel: PrefsViewModel) {
    val usernameState = remember { mutableStateOf("") }
    val endpointState = remember { mutableStateOf("") }

    val usernamePref = prefsViewModel.username.collectAsState(initial = null).value
    val userIdPref = prefsViewModel.userId.collectAsState(initial = null).value
    val totpSecretPref = prefsViewModel.totpSecret.collectAsState(initial = null).value
    val endpointPref = prefsViewModel.endpoint.collectAsState(initial = null).value

    val shouldShowInputs = listOf(usernamePref, userIdPref, totpSecretPref, endpointPref).any { it == null }

    Base(title = "Home Page") {
        if (shouldShowInputs) {
            InputForm(
                usernameState = usernameState,
                endpointState = endpointState,
                onSaveClick = { username ->
                    prefsViewModel.generateUser(username, "123")
                }
            )
        } else {
            TotpView(
                username = usernamePref,
                totpSecret = totpSecretPref
            )
        }
    }
}

@Composable
private fun TotpView(username: String?, totpSecret: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back ${username ?: ""}",
            color = AppColors.TextPrimary.copy(alpha = 0.60f),
            fontSize = 32.sp,
            letterSpacing = 1.1.sp,
            fontWeight = FontWeight.W300,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        totpSecret?.let { secret ->
            TotpGenerator(secret = secret)
        }
    }
}

@Composable
private fun InputForm(
    usernameState: MutableState<String>,
    endpointState: MutableState<String>,
    onSaveClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Please provide the required data.\n",
            color = AppColors.TextPrimary,
            fontSize = 24.sp
        )

        Text(
            text = "Don't worry, you will be able to change this later!",
            color = AppColors.TextSecondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )

        CustomOutlinedTextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = "Username"
        )

        CustomOutlinedTextField(
            value = endpointState.value,
            onValueChange = { endpointState.value = it },
            label = "Endpoint"
        )

        Button(
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
            onClick = { onSaveClick(usernameState.value) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Save",
                fontSize = 22.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun CustomOutlinedTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = AppColors.Slate.copy(alpha = 0.75f),
            focusedBorderColor = AppColors.Primary,
            focusedLabelColor = AppColors.TextPrimary,
            unfocusedTextColor = AppColors.TextPrimary,
            unfocusedLabelColor = AppColors.TextPrimary.copy(alpha = 0.75f),
            focusedTextColor = AppColors.TextPrimary,
        )
    )
}

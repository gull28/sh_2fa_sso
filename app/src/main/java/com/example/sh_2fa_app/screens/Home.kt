package com.example.sh_2fa_app.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sh_2fa_app.data.PrefsViewModel
import com.example.sh_2fa_app.ui.AppColors
import com.example.sh_2fa_app.ui.components.Base

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, prefsViewModel: PrefsViewModel) {
    val username = remember { mutableStateOf("") }
    val endpoint = remember { mutableStateOf("") }

    val usernamePref = prefsViewModel.username.collectAsState(initial = null).value
    val userIdPref = prefsViewModel.userId.collectAsState(initial = null).value
    val totpSecretPref = prefsViewModel.totpSecret.collectAsState(initial = null).value
    val endpointPref = prefsViewModel.endpoint.collectAsState(initial = null).value

    val shouldShowInputs = usernamePref == null || userIdPref == null || totpSecretPref == null || endpointPref == null

    Base(title = "Home Page") {
        if (shouldShowInputs) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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

                OutlinedTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text("Username") },
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

                OutlinedTextField(
                    value = endpoint.value,
                    onValueChange = { endpoint.value = it },
                    label = { Text("Endpoint") },
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

                // Save Button
                Button(
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary),
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
                    onClick = {
                        prefsViewModel.generateUser(username.value.toString(), "123")
                    },
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
    }
}

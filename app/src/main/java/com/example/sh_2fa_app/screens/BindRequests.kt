package com.example.sh_2fa_app.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
import com.example.sh_2fa_app.ui.components.Base
import com.example.sh_2fa_app.ui.components.ServiceItem


@Composable
fun BindRequests(viewModel: PrefsViewModel) {
    val bindRequests by viewModel.bindRequests.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchBindRequests()
    }
    Base(title = "Bind requests") {
        LazyColumn {
            if (bindRequests.isEmpty()) {
                item {
                    Text(
                        text = "No services available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            } else {
                itemsIndexed(bindRequests) { _, item ->
                    Row {
                        Text(text = item.serviceId, color = Color.White, fontSize = 20.sp)


                    }
                }
            }
        }
    }
}
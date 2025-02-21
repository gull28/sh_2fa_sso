package com.example.sh_2fa_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
import com.example.sh_2fa_app.data.models.ServiceItem
import com.example.sh_2fa_app.data.models.UnboundServiceItem

@Composable
fun ServiceAdapter(prefsViewModel: PrefsViewModel) {
    val services by prefsViewModel.services.observeAsState(initial = emptyList())
    val unboundServices by prefsViewModel.unboundServices.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        prefsViewModel.fetchServices()
    }

    Column {
        Text(text = "Bound services", color = Color.White, fontSize = 26.sp)


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 24.dp)
        ) {
            if (services.isEmpty()) {
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
                itemsIndexed(services) { _, item ->
                    ServiceItem(serviceItem = item, unBindService = {
                        prefsViewModel.unbindService(
                            item.userServiceLinkId
                        )
                    })
                }
            }
        }

        Text(text = "Unbound services", color = Color.White, fontSize = 26.sp)


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 24.dp)
        ) {
            if (unboundServices.isEmpty()) {
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
                itemsIndexed(unboundServices) { _, item ->
                    UnboundServiceItem(unboundServiceItem = item)
                }
            }
        }
    }
}

@Composable
fun ServiceItem(serviceItem: ServiceItem, unBindService: (id: UInt) -> Unit) {
    Row {
        Text(text = serviceItem.name, color = Color.White, fontSize = 20.sp)

        Button(onClick = { unBindService(serviceItem.userServiceLinkId) }) {
            Text(text = "Unbind")
        }
    }
}

@Composable
fun UnboundServiceItem(unboundServiceItem: UnboundServiceItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = unboundServiceItem.name,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )
    }
}
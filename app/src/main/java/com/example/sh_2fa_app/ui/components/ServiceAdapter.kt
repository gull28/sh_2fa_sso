package com.example.sh_2fa_app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sh_2fa_app.data.PrefsViewModel
import com.example.sh_2fa_app.data.models.ServiceItem
import com.example.sh_2fa_app.ui.AppColors

@Composable
fun ServiceAdapter(prefsViewModel: PrefsViewModel) {
    val services by prefsViewModel.services.observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 24.dp)
    ) {
        itemsIndexed(services) { index, item ->
            ServiceItem(serviceItem = item)
        }
    }
}

@Composable
fun ServiceItem(serviceItem: ServiceItem) {
    Row {
        Text(text = serviceItem.name, color = AppColors.TextPrimary)
    }
}
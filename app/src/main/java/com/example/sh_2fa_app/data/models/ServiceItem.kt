package com.example.sh_2fa_app.data.models

import com.squareup.moshi.Json
import java.sql.Time

data class ServiceItem(
    @Json(name = "serviceId") val serviceId: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "validUntil") val validUntil: Time,
)
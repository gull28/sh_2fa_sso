package com.example.sh_2fa_app.data.models

import com.squareup.moshi.Json
import java.util.Date

data class BindRequest (
    @Json(name = "id") val id: UInt,
    @Json(name = "serviceId") val serviceId: String,
    @Json(name = "userId") val userId: String,
    @Json(name = "serviceUserId") val serviceUserId: String,
    @Json(name = "valid_until") val validUntil: Date
)
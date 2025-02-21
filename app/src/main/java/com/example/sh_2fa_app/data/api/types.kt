package com.example.sh_2fa_app.data.api

import com.example.sh_2fa_app.data.models.BindRequest
import com.example.sh_2fa_app.data.models.ServiceItem
import com.example.sh_2fa_app.data.models.UnboundServiceItem
import com.squareup.moshi.Json

data class CreateUserResponse(
    val id: String,
    val username: String,
    val totp_secret: String
)

data class CreateUserRequest(
    val username: String
)

data class FetchServicesRequest(
    val authUserId: String
)

data class FetchServiceResponse(
    @Json(name = "services") val services: List<ServiceItem>,
    @Json(name = "nonBoundServices")  val nonBoundServices: List<UnboundServiceItem>
)

data class FetchBindRequestsResponse(
    @Json(name = "bindRequests") val bindRequests: List<BindRequest>
)
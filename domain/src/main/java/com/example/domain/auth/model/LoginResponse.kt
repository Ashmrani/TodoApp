package com.example.domain.auth.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("mobile")
    val mobile: String
)
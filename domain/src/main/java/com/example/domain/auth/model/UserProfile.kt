package com.example.domain.auth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserProfile(

    @field:SerializedName("access_token")
    val accessToken: String? = null,

    @field:SerializedName("user")
    val user: User? = null
) : Serializable

data class Role(

    @field:SerializedName("role_name")
    val roleName: String? = null,

    @field:SerializedName("role_id")
    val roleId: Int? = null
) : Serializable

data class User(

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("email_activated")
    val emailActivated: Boolean? = null,

    @field:SerializedName("role")
    val role: Role? = null,

    @field:SerializedName("blocked")
    val blocked: Boolean? = null,

    @field:SerializedName("gender")
    val gender: Any? = null,

    @field:SerializedName("user_id")
    val userId: String? = null,

    @field:SerializedName("role_id")
    val roleId: Int? = null,

    @field:SerializedName("image_url")
    val imageUrl: Any? = null,

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("block_reason")
    val blockReason: Any? = null,

    @field:SerializedName("mobile_activated")
    val mobileActivated: Boolean? = null,

    @field:SerializedName("email")
    val email: Any? = null
) : Serializable
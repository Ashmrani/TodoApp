package com.example.domain.auth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserProfile(

    @field:SerializedName("access_token")
    var accessToken: String? = null,

    @field:SerializedName("user")
    var user: User? = null
) : Serializable

data class Role(

    @field:SerializedName("role_name")
    var roleName: String? = null,

    @field:SerializedName("role_id")
    var roleId: Int? = null
) : Serializable

data class User(

    @field:SerializedName("full_name")
    var fullName: String? = null,

    @field:SerializedName("email_activated")
    var emailActivated: Boolean? = null,

    @field:SerializedName("role")
    var role: Role? = null,

    @field:SerializedName("blocked")
    var blocked: Boolean? = null,

    @field:SerializedName("gender")
    var gender: Any? = null,

    @field:SerializedName("user_id")
    var userId: String? = null,

    @field:SerializedName("role_id")
    var roleId: Int? = null,

    @field:SerializedName("image_url")
    var imageUrl: Any? = null,

    @field:SerializedName("mobile")
    var mobile: String? = null,

    @field:SerializedName("block_reason")
    var blockReason: Any? = null,

    @field:SerializedName("mobile_activated")
    var mobileActivated: Boolean? = null,

    @field:SerializedName("email")
    var email: Any? = null
) : Serializable
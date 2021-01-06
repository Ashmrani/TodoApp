package com.example.domain.todo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Todo(

    @field:SerializedName("todo_id")
    val todoId: String? = null,

    @field:SerializedName("user_id")
    val userId: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null
) : Serializable

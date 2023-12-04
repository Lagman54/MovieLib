package com.example.finalproject.data.network.model

import com.google.gson.annotations.SerializedName

data class VideoEntity(
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String
)
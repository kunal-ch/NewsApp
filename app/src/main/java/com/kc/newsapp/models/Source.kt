package com.kc.newsapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    val id: String,
    val name: String
) : Serializable
package dev.meetap.mvp.home.model

import com.google.gson.annotations.SerializedName

data class RumahItemNullable(
        @SerializedName("nama") val author: String? = null,
        @SerializedName("alamat") val description: String? = null,
        @SerializedName("harga") val title: String? = null
)
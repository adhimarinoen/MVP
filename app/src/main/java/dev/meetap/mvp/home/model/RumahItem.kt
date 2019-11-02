package dev.meetap.mvp.home.model

import com.google.gson.annotations.SerializedName

data class RumahItem(
        @SerializedName("nama") var nama: String,
        @SerializedName("alamat") var alamat: String,
        @SerializedName("harga") var harga: String
)
package dev.meetap.mvp.home.model

import com.google.gson.annotations.SerializedName

data class DataResponseNullable(
        @SerializedName("records") val records: List<RumahItemNullable?>? = null
)
package com.devtides.androidcoroutinesretrofit.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") @JvmField val countryName: String?,
    @SerializedName("capital") @JvmField val capital: String?,
    @SerializedName("flagPNG") @JvmField val flag: String?
)
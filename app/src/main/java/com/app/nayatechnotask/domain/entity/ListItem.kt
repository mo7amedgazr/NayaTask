package com.app.nayatechnotask.domain.entity

import com.google.gson.annotations.SerializedName

data class ListItem(

    @field:SerializedName("badges")
    val badges: List<String?>? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("sku")
    val sku: String? = null,

    @field:SerializedName("brand")
    val brand: String? = null,

    @field:SerializedName("originalPrice")
    val originalPrice: Int? = null,

    var saved: Boolean = false
)
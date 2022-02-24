package com.app.nayatechnotask.data.common.response

import com.app.nayatechnotask.domain.entity.ListItem
import com.google.gson.annotations.SerializedName

data class ItemsListResponse(

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("items")
	val items: List<ListItem>? = null
)
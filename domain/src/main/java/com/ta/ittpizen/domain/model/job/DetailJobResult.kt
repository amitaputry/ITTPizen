package com.ta.ittpizen.domain.model.job

data class DetailJobResult(

	val id: String = "",

	val userId: String = "",

	val title: String = "",

	val company: String = "",

	val street: String = "",

	val city: String = "",

	val province: String = "",

	val workplaceType: String = "",

	val jobType: String = "",

	val description: String = "",

	val skills: List<String> = emptyList(),

	val experience: String = "",

	val graduates: String = "",

	val link: String = "",

	val saved: Boolean = false,

	val createdAt: String = ""

)

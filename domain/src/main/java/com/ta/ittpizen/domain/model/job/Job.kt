package com.ta.ittpizen.domain.model.job

data class Job(

	val id: String = "",

	val userId: String = "",

	val title: String = "",

	val company: String = "",

	val province: String = "",

	val city: String = "",

	val saved: Boolean = false,

	val street: String = "",

	val graduates: String = "",

	val workplaceType: String = "",

	val jobType: String = "",

	val createdAt: String = ""
)

fun Job.getLocation(): String {
	return "$street, $city, $province"
}

fun Job.getCharacteristics(): List<String> {
	return listOf(
		workplaceType,
		jobType,
		graduates
	)
}

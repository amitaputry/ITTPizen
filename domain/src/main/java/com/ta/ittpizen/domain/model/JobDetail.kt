package com.ta.ittpizen.domain.model

data class JobDetail(
    val id: String = "",
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
    val date: String = "",
    val saved: Boolean = false,
)

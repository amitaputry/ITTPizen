package com.ta.ittpizen.domain.model

data class JobItem(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val characteristics: List<String> = emptyList(),
    val location: String = "",
    val date: String = "",
    val saved: Boolean = false,
)

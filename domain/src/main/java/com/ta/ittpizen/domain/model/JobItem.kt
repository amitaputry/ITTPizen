package com.ta.ittpizen.domain.model

enum class JobItemType {
    REMOTE,
    ONSITE,
    FULL_TIME,
    PART_TIME,
    INTERNSHIP,
    VOLUNTEER
}

data class JobItem(
    val id: String = "",
    val type: JobItemType = JobItemType.ONSITE,
    val name: String = "",
    val company: String = "",
    val characteristics: List<String> = emptyList(),
    val location: String = "",
    val date: String = "",
    val saved: Boolean = false,
)

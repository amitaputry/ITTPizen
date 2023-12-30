package com.ta.ittpizen.domain.model

enum class UserItemType {
    Student, Alumni, Lecturer, Staff
}

data class UserItem(
    val id: String = "",
    val userType: UserItemType = UserItemType.Student,
    val photo: String = "",
    val name: String = "",
    val type: String = "",
    val connected: Boolean = false
)

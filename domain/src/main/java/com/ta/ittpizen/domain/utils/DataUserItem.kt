package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.UserItem
import com.ta.ittpizen.domain.model.UserItemType

object DataUserItem {

    private val allUsers = mutableListOf<UserItem>(
        UserItem(
            id = "1",
            name = "Daffa Rayhan Riadi",
            type = "Student"
        ),
        UserItem(
            id = "2",
            name = "Abdul Hafiz Ramadan",
            type = "Student"
        ),
        UserItem(
            id = "3",
            name = "Afifatunni’mah",
            type = "Student"
        ),
        UserItem(
            id = "4",
            name = "Raihan Febriyansah",
            type = "Student"
        ),
        UserItem(
            id = "5",
            name = "Ajib Syah Abad",
            type = "Student"
        ),
        UserItem(
            id = "6",
            name = "Ahmad Nawawi",
            type = "Student"
        ),
        UserItem(
            id = "7",
            name = "Rasyid Ramadhani",
            type = "Student"
        ),
        UserItem(
            id = "8",
            name = "Alif Rizki Ramdhana",
            type = "Student"
        ),
        UserItem(
            id = "123",
            name = "GDSC IT Telkom Purwo..",
            type = "Student"
        ),
        UserItem(
            id = "9",
            userType = UserItemType.Alumni,
            name = "Yolanda Al Hidayah Pasaribu",
            type = "Alumni"
        ),
        UserItem(
            id = "10",
            userType = UserItemType.Alumni,
            name = "Dery Sudrajat",
            type = "Alumni"
        ),
        UserItem(
            id = "11",
            userType = UserItemType.Alumni,
            name = "Brian Nur Hilmawan",
            type = "Alumni"
        ),
        UserItem(
            id = "12",
            userType = UserItemType.Alumni,
            name = "Vito Raditya Fauzan",
            type = "Alumni"
        ),
        UserItem(
            id = "13",
            userType = UserItemType.Alumni,
            name = "Irfan Mulyana Abdillah",
            type = "Alumni"
        ),
        UserItem(
            id = "14",
            userType = UserItemType.Alumni,
            name = "Gamaliel Widhi Pradana",
            type = "Alumni"
        ),
        UserItem(
            id = "15",
            userType = UserItemType.Alumni,
            name = "Bagus Bayu Sasongko",
            type = "Alumni"
        ),
        UserItem(
            id = "16",
            userType = UserItemType.Alumni,
            name = "Gracia Rizka Pasfica",
            type = "Alumni"
        ),
        UserItem(
            id = "17",
            userType = UserItemType.Lecturer,
            name = "Gita Fadila Fitriana",
            type = "Lecturer"
        ),
        UserItem(
            id = "18",
            userType = UserItemType.Lecturer,
            name = "Faisal Dharma Adhinata",
            type = "Lecturer"
        ),
        UserItem(
            id = "19",
            userType = UserItemType.Lecturer,
            name = "Arif Amrulloh",
            type = "Lecturer"
        ),
        UserItem(
            id = "20",
            userType = UserItemType.Lecturer,
            name = "Alon Jala Tirta Segara",
            type = "Lecturer"
        ),
        UserItem(
            id = "21",
            userType = UserItemType.Lecturer,
            name = "Yogo Dwi Prasetyo",
            type = "Lecturer"
        ),
        UserItem(
            id = "22",
            userType = UserItemType.Lecturer,
            name = "Abednego Dwi Septiadi",
            type = "Lecturer"
        ),
        UserItem(
            id = "23",
            userType = UserItemType.Lecturer,
            name = "Ariq Cahya Wardhana",
            type = "Lecturer"
        ),
        UserItem(
            id = "24",
            userType = UserItemType.Lecturer,
            name = "Nia Annisa Ferani Tanjung",
            type = "Lecturer"
        ),
        UserItem(
            id = "25",
            userType = UserItemType.Staff,
            name = "Siti Aisyah",
            type = "Staff"
        ),
        UserItem(
            id = "26",
            userType = UserItemType.Staff,
            name = "Bambang Wijayanto",
            type = "Staff"
        ),
        UserItem(
            id = "27",
            userType = UserItemType.Staff,
            name = "Tia Ayu Dewi",
            type = "Staff"
        ),
        UserItem(
            id = "28",
            userType = UserItemType.Staff,
            name = "Mulyono Rohmah",
            type = "Staff"
        ),
        UserItem(
            id = "29",
            userType = UserItemType.Staff,
            name = "Agus Susilowani",
            type = "Staff"
        ),
        UserItem(
            id = "30",
            userType = UserItemType.Staff,
            name = "Budi Hartiano",
            type = "Staff"
        ),
        UserItem(
            id = "31",
            userType = UserItemType.Staff,
            name = "Muhammad Hendri",
            type = "Staff"
        ),
        UserItem(
            id = "32",
            userType = UserItemType.Staff,
            name = "Sukarno Budianto",
            type = "Staff"
        ),
        UserItem(
            id = "33",
            userType = UserItemType.Staff,
            name = "Kemahasiswaan ITTP",
            type = "Staff"
        ),
        UserItem(
            id = "34",
            userType = UserItemType.Staff,
            name = "Novanda Alim Setya N..",
            type = "Staff"
        ),
        UserItem(
            id = "35",
            userType = UserItemType.Staff,
            name = "Pusat Karir ITTP",
            type = "Staff"
        ),
    )

    fun getUserById(id: String): UserItem? = allUsers.find { it.id == id }

    fun generateUsersByType(type: UserItemType) = allUsers.filter {
        it.userType == type
    }

    fun connectToUser(userItem: UserItem): UserItem? {
        val selectedUser = allUsers.find { it.id == userItem.id }  ?: return null
        val connected = selectedUser.connected
        val updatedUser = selectedUser.copy(connected = connected.not())
        allUsers.replaceAll {
            if (it.id == userItem.id) updatedUser else it
        }
        return updatedUser
    }

    fun searchUsers(query: String): List<UserItem> = allUsers.filter {
        it.name.contains(query, ignoreCase = true)
    }

}
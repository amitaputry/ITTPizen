package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.Profile

object DataProfile {

    private val allProfile = mutableListOf(
        Profile(
            id = "1",
            userId = "1",
            name = "Daffa Rayhan Riadi",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "2",
            userId = "2",
            name = "Abdul Hafiz Ramadan",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "3",
            userId = "3",
            name = "Afifatunni’mah",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "4",
            userId = "4",
            name = "Raihan Febriyansah",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "5",
            userId = "5",
            name = "Ajib Syah Abad",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "6",
            userId = "6",
            name = "Ahmad Nawawi",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "7",
            userId = "7",
            name = "Rasyid Ramadhani",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "8",
            userId = "8",
            name = "Alif Rizki Ramdhana",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "123",
            userId = "123",
            name = "GDSC IT Telkom Purwo..",
            type = "Student",
            bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "9",
            userId = "9",
            name = "Yolanda Al Hidayah Pasaribu",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "10",
            userId = "10",
            name = "Dery Sudrajat",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "11",
            userId = "11",
            name = "Brian Nur Hilmawan",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "12",
            userId = "12",
            name = "Vito Raditya Fauzan",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "13",
            userId = "13",
            name = "Irfan Mulyana Abdillah",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "14",
            userId = "14",
            name = "Gamaliel Widhi Pradana",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "15",
            userId = "15",
            name = "Bagus Bayu Sasongko",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "16",
            userId = "16",
            name = "Gracia Rizka Pasfica",
            type = "Alumni",
            bio = "I am Software Engineering Alumni at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "17",
            userId = "17",
            name = "Gita Fadila Fitriana",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "18",
            userId = "18",
            name = "Faisal Dharma Adhinata",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "19",
            userId = "19",
            name = "Arif Amrulloh",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "20",
            userId = "20",
            name = "Alon Jala Tirta Segara",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "21",
            userId = "21",
            name = "Yogo Dwi Prasetyo",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "22",
            userId = "22",
            name = "Abednego Dwi Septiadi",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "23",
            userId = "23",
            name = "Ariq Cahya Wardhana",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "24",
            userId = "24",
            name = "Nia Annisa Ferani Tanjung",
            type = "Lecturer",
            bio = "I am Software Engineering Lecturer at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "25",
            userId = "25",
            name = "Siti Aisyah",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "26",
            userId = "26",
            name = "Bambang Wijayanto",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "27",
            userId = "27",
            name = "Tia Ayu Dewi",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "28",
            userId = "28",
            name = "Mulyono Rohmah",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "29",
            userId = "29",
            name = "Agus Susilowani",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "30",
            userId = "30",
            name = "Budi Hartiano",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "31",
            userId = "31",
            name = "Muhammad Hendri",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "32",
            userId = "32",
            name = "Sukarno Budianto",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "33",
            userId = "33",
            name = "Kemahasiswaan ITTP",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "34",
            userId = "34",
            name = "Novanda Alim Setya N..",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
        Profile(
            id = "35",
            userId = "35",
            name = "Pusat Karir ITTP",
            type = "Staff",
            bio = "I am Software Engineering Staff at Telkom Institute of Technology Purwokerto"
        ),
    )

    fun getProfileById(id: String): Profile? = allProfile.find { it.id == id }

    fun connectToProfile(userItem: Profile): Profile? {

        val selectedUser = DataUserItem.getUserById(userItem.userId) ?: return null
        DataUserItem.connectToUser(selectedUser)

        val selectedProfile = allProfile.find { it.id == userItem.id }  ?: return null
        val connected = selectedProfile.connected
        val updatedUser = selectedProfile.copy(connected = connected.not())
        allProfile.replaceAll {
            if (it.id == userItem.id) updatedUser else it
        }
        return updatedUser
    }

    fun updateConnectState(id: String, connected: Boolean) {
        val selectedProfile = allProfile.find { it.id == id }  ?: return
        val updatedUser = selectedProfile.copy(connected = connected)
        allProfile.replaceAll {
            if (it.id == id) updatedUser else it
        }
    }

}
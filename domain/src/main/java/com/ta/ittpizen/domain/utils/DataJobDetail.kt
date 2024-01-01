package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.JobDetail

object DataJobDetail {

    private val allJobs = mutableListOf(
        JobDetail(
            id = "0",
            title = "Android Developer",
            company = "Tokopedia",
            city = "Jakarta, Indonesia",
            jobType = "Full Time",
            workplaceType = "Remote",
            date = "10 days ago",
            experience = "3 - 5 year",
            graduates = "Min S1",
            description = "Design and develop applications: Developers should be aware of how to deal with device fragmentation and work closely with designers to achieve the best user experience.",
            skills = listOf(
                "Android Studio",
                "Kotlin",
                "XML/Compose",
                "Communication",
                "Git"
            ),
            link = "https://google.com"
        ),
        JobDetail(
            id = "1",
            title = "Asisten Praktikum Basis Data",
            company = "Fakultas Informatika",
            street = "Jl. DI Panjaitan No.128",
            city = "Banyumas",
            province = "Jawa Tengah",
            jobType = "Part time",
            workplaceType = "Onsite",
            date = "12 days ago",
            experience = "3 - 5 year",
            graduates = "Min S1",
            description = "Asisten praktikum basis data memiliki tugas yang sama dengan asisten dosen, yaitu bertanggung jawab pada mata kuliah praktikum.",
            skills = listOf(
                "Python",
                "Data structure",
                "Algoritma pemrograman"
            ),
            link = "https://google.com"
        ),
        JobDetail(
            id = "2",
            title = "Content Creator",
            company = "PT Graha Indonesia",
            street = "Dukuh Pakis",
            city = "Surabaya",
            province = "Jawa Timur",
            date = "9 days ago",
            jobType = "Full time",
            workplaceType = "Onsite",
            experience = "3 - 5 year",
            graduates = "Min S1",
            description = "Content creators should also be able to promote content on social media. They should be self-motivated and able to work independently. They should also have experience with digital publishing and generating traffic and leads for new business.",
            skills = listOf(
                "Choose a niche",
                "Read content about your industry every day",
                "Write regularly",
                "Study your audience",
                "Establish your own voice",
                "Curate other people's content when it makes sense",
                "Understand your KPIs",
                "Network at every opportunity"
            ),
            link = "https://google.com"
        ),
    )

    fun getJobDetailById(id: String): JobDetail? = allJobs.find {
        it.id == id
    }


}
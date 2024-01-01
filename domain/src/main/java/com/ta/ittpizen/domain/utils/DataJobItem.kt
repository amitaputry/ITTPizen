package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.JobItem

object DataJobItem {

    private val allJobs = mutableListOf(
        JobItem(
            id = "0",
            name = "Android Developer",
            characteristics = listOf("Remote", "Full Time", " 3 - 5 year", "Min S1"),
            company = "Tokopedia",
            location = "Jakarta, Indonesia",
            date = "10 days ago"
        ),
        JobItem(
            id = "1",
            name = "Asisten Praktikum Basis Data",
            characteristics = listOf("Onsite", "Part time", " 3 - 5 year", "Min S1"),
            company = "Fakultas Informatika",
            location = "Jl. DI Panjaitan No.128, Banyumas, Jawa Tengah",
            date = "12 days ago"
        ),
        JobItem(
            id = "2",
            name = "Content Creator",
            characteristics = listOf("Onsite", "Full time", " 3 - 5 year", "Min S1"),
            company = "PT Graha Indonesia",
            location = "Dukuh Pakis, Surabaya, Jawa Timur",
            date = "9 days ago"
        ),
    )

    fun getAllJobsItem(): List<JobItem> = allJobs

    fun getJobItemById(id: String): JobItem? = allJobs.find { it.id == id }

    fun getJobsItemByCharacteristic(character: String): List<JobItem> = allJobs.filter {
        it.characteristics.contains(character)
    }

    fun getSavedJobs(): List<JobItem> = allJobs.filter {
        it.saved
    }

    fun searchJob(query: String): List<JobItem> = allJobs.filter {
        it.name.contains(query) || it.location.contains(query)
    }

    fun savedOrUnsavedJob(job: JobItem): JobItem? {
        val selectedPost = allJobs.find { it.id == job.id }  ?: return null
        val saved = selectedPost.saved
        val updatedJob = selectedPost.copy(
            saved = saved.not()
        )
        allJobs.replaceAll {
            if (it.id == job.id) updatedJob else it
        }
        return updatedJob
    }


}
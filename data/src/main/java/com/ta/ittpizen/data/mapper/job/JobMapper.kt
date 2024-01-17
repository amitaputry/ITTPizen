package com.ta.ittpizen.data.mapper.job

import com.ta.ittpizen.data.remote.response.job.JobResponse
import com.ta.ittpizen.domain.model.job.Job

fun List<JobResponse>.toDomains(): List<Job> = map {
    it.toDomain()
}

fun JobResponse.toDomain(): Job = Job(
    id = id ?: "",
    userId = userId ?: "",
    title = title ?: "",
    company = company ?: "",
    province = province ?: "",
    city = city ?: "",
    saved = saved ?: false,
    street = street ?: "",
    graduates = graduates ?: "",
    workplaceType = workplaceType ?: "",
    jobType = jobType ?: "",
    createdAt = createdAt ?: ""
)

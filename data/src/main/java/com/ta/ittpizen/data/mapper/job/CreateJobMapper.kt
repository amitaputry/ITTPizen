package com.ta.ittpizen.data.mapper.job

import com.ta.ittpizen.data.remote.response.job.CreateJobResponse
import com.ta.ittpizen.data.remote.response.job.CreateJobResult

fun CreateJobResponse.toDomain(): CreateJobResult = CreateJobResult(
    id = id ?: "",
    userId = userId ?: "",
    title = title ?: "",
    company = company ?: "",
    street = street ?: "",
    city = city ?: "",
    province = province ?: "",
    workplaceType = workplaceType ?: "",
    jobType = jobType ?: "",
    description = description ?: "",
    skills = skills ?: emptyList(),
    experience = experience ?: "",
    graduates = graduates ?: "",
    link = link ?: "",
    createdAt = createdAt ?: ""
)

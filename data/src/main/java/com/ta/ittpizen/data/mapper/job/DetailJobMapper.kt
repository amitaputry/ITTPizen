package com.ta.ittpizen.data.mapper.job

import com.ta.ittpizen.data.remote.response.job.DetailJobResponse
import com.ta.ittpizen.domain.model.job.DetailJobResult

fun DetailJobResponse.toDomain(): DetailJobResult = DetailJobResult(
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
    saved = saved ?: false,
    createdAt = createdAt ?: ""
)

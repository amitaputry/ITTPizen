package com.ta.ittpizen.data.mapper.connection

import com.ta.ittpizen.data.remote.response.connection.ConnectionResponse
import com.ta.ittpizen.domain.model.connection.Connection

fun List<ConnectionResponse>.toDomains(): List<Connection> = map {
    it.toDomain()
}

fun ConnectionResponse.toDomain(): Connection = Connection(
    id = id ?: "",
    photo = photo ?: "",
    name = name ?: "",
    type = type ?: "",
    connected = connected ?: false
)

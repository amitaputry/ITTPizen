package com.ta.ittpizen.data.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.ta.ittpizen.data.UserPreference
import java.io.InputStream
import java.io.OutputStream

val Context.userPreferenceDataStore: DataStore<UserPreference> by dataStore(
    fileName = "user_preference.pb",
    serializer = UserPreferenceSerializer
)

object UserPreferenceSerializer : Serializer<UserPreference> {

    override val defaultValue: UserPreference
        get() = UserPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreference {
        try {
            return UserPreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreference, output: OutputStream) = t.writeTo(output)

}

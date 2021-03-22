package br.com.zup.shared

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun Timestamp.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds,nanos.toLong()), ZoneOffset.UTC)
}

fun LocalDateTime.toTimestamp(): Timestamp{
    return atZone(ZoneId.of("UTC")).toInstant().let {
        com.google.protobuf.Timestamp.newBuilder()
            .setNanos(it.nano)
            .setSeconds(it.epochSecond)
            .build()
    }
}
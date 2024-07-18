package core.entities

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException
import okio.IOException

sealed interface Failure {
    data class NetworkFailure(val code: Int): Failure
    data class IOFailure(val message: String? = null): Failure
    data class UnknownFailure(val message: String? = null): Failure
    data object AssertionFailure: Failure
}

fun Throwable.toFailure(): Failure = when (this) {
    is ClientRequestException -> Failure.NetworkFailure(response.status.value)
    is ServerResponseException -> Failure.NetworkFailure(response.status.value)
    is SerializationException -> Failure.IOFailure(message)
    is IOException -> Failure.IOFailure(message)
    is IllegalStateException -> Failure.AssertionFailure
    is IllegalArgumentException -> Failure.AssertionFailure
    else -> Failure.UnknownFailure(message)
}
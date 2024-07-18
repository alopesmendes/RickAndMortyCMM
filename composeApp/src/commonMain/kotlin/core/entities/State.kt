package core.entities

sealed interface State<out T> {
    data object Loading : State<Nothing>
    data class Success<T>(val data: T) : State<T>
    data class Error(val failure: Failure) : State<Nothing>
}

inline fun <T, R> State<T>.map(transform: (T) -> R): State<R> = when (this) {
    is State.Loading -> this
    is State.Error -> this
    is State.Success -> State.Success(transform(data))
}

fun <T> State<T>.getOrNull(): T? = when (this) {
    is State.Loading -> null
    is State.Error -> null
    is State.Success -> data
}
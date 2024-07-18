package core.util

object Tools {
    fun String.extractIdFromUrl(): Int {
        return split("/").last().toInt()
    }
}
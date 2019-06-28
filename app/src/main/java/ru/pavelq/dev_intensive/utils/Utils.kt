package ru.pavelq.dev_intensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //fix
        val parts: List<String>? = fullName?.split(" ")
        val firstName: String? = nullOnEmptyString(parts?.getOrNull(0))
        val lastName: String? = nullOnEmptyString(parts?.getOrNull(1))
        return Pair(firstName, lastName)
    }

    private fun nullOnEmptyString(text: String?): String? {
        return if (text?.trim().isNullOrEmpty()) null else text
    }

    fun transliteration(payload: String, devider: String = " "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        TODO("not implemented")
    }
}
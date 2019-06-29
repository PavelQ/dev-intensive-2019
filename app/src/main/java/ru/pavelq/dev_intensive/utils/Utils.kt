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
        return if (text.isNullOrBlank()) null else text
    }

    fun transliteration(payload: String, devider: String = " "): String {
        val parts = payload.split(" ")
        val translatinatedParts = mutableListOf<String>()
        for (part in parts) {
            translatinatedParts.add(transliteration(part))
        }

        return translatinatedParts.joinToString(devider)
    }

    fun transliteration(word: String): String {
        val builder = StringBuilder()
        for (char in word) {
            val wasUpper = char.isUpperCase()
            val loverChar = if (wasUpper) char.toLowerCase() else char
            var trCrar = transliterationMap[loverChar.toString()]
            if (trCrar != null) {
                if (wasUpper) trCrar = trCrar.toUpperCase()
                builder.append(trCrar)
            } else
                builder.append(char)
        }
        return builder.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) return null
        else if (firstName.isNullOrBlank()) return toInitial(lastName!!)
        else if (lastName.isNullOrBlank()) return toInitial(firstName)
        else return toInitial(firstName) + toInitial(lastName)
    }

    fun toInitial(name: String): String {
        return name.first().toUpperCase().toString()
    }


    private val transliterationMap = initTransliterationMap()

    private fun initTransliterationMap(): Map<String, String> {
        val map = hashMapOf<String, String>()

        map.put("а", "a")
        map.put("б", "b")
        map.put("в", "v")
        map.put("г", "g")
        map.put("д", "d")
        map.put("е", "e")
        map.put("ё", "e")
        map.put("ж", "zh")
        map.put("з", "z")
        map.put("и", "i")
        map.put("й", "i")
        map.put("к", "k")
        map.put("л", "l")
        map.put("м", "m")
        map.put("н", "n")
        map.put("о", "o")
        map.put("п", "p")
        map.put("р", "r")
        map.put("с", "s")
        map.put("т", "t")
        map.put("у", "u")
        map.put("ф", "f")
        map.put("х", "h")
        map.put("ц", "c")
        map.put("ч", "ch")
        map.put("ш", "sh")
        map.put("щ", "sh'")
        map.put("ъ", "")
        map.put("ы", "i")
        map.put("ь", "")
        map.put("э", "e")
        map.put("ю", "yu")
        map.put("я", "ya")
        return map
    }

}
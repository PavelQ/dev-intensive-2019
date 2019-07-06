package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    val finisherString = "..."
    val space = ' '
    var requeredFinisher = false
    var subs = this.trimEnd(space)
    if (len < subs.length) {
        requeredFinisher = true
        subs = subs.substring(0, len)
    }
    subs = subs.trimEnd(space)
    if (requeredFinisher) {
        subs += finisherString
    }
    return subs
}

/**
Реализуй extension позволяющий очистить строку от html тегов и html escape последовательностей ("& < > ' ""),
а так же удалить пустые символы (пробелы) между словами если их больше 1. Необходимо вернуть модифицированную строку
Пример:
"<p class="title">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
 */

fun String.stripHtml(): String {
    val withoutHtml = "(<[^>]*>|[&'\"])".toRegex().replace(this, "")
    val withouBigSpaces = " {2,}".toRegex().replace(withoutHtml, " ")
    return withouBigSpaces
}

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
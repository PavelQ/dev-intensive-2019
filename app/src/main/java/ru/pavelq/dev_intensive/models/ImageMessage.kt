package ru.pavelq.dev_intensive.models

import ru.pavelq.dev_intensive.extentions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date,
    val image: String
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id:$id ${from?.firstName} " +
            "${if (isIncoming) "получил" else "отправил"} изображение  \"$image\" ${date.humanizeDiff()}"


}
package ru.pavelq.dev_intensive.extentions

import ru.pavelq.dev_intensive.models.User
import ru.pavelq.dev_intensive.models.UserView
import ru.pavelq.dev_intensive.utils.Utils

fun User.toUserView(): UserView? {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if (lastVisit == null) "еще ни разу не был" else if (isOnline) "online" else lastVisit!!.humanizeDiff()
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        initials = initials,
        avatar = avatar,
        status = status
    )
}


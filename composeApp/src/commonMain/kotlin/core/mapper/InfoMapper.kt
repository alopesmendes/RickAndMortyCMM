package core.mapper

import core.entities.Info
import core.entities.InfoItem
import core.models.InfoDto

fun InfoDto.mapTo(): Info = Info(
    count = count,
    pages = pages,
    next = next,
    prev = prev,
)

fun Info.mapTo(): InfoItem = InfoItem(
    count = count,
    pages = pages,
    next = next,
    prev = prev,
)
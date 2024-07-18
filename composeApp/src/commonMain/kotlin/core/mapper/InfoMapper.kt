package core.mapper

import core.entities.Info
import core.models.InfoDto

inline fun InfoDto.mapTo(): Info = Info(
    count = count,
    pages = pages,
    next = next,
    prev = prev,
)
package com.remcoil.data.model.action

import com.remcoil.utils.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActionWithComment(
    val id: Long,
    @SerialName("operator_id")
    var operatorId: Int,
    @SerialName("bobbin_id")
    val bobbinId: Long,
    @SerialName("action_type")
    val actionType: String,
    @SerialName("done_time")
    @Serializable(with = LocalDateTimeSerializer::class)
    val doneTime: LocalDateTime,
    var successful: Boolean = false,
    val comment: String
) {
    constructor(actionWithComment: ActionWithCommentDto, operatorId: Int) : this(
        actionWithComment.id,
        operatorId,
        actionWithComment.bobbinId,
        actionWithComment.actionType,
        actionWithComment.doneTime,
        actionWithComment.successful,
        actionWithComment.comment
    )
}
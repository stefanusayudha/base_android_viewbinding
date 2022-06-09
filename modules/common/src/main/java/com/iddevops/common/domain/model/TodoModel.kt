package com.iddevops.common.domain.model

import com.iddevops.core.common.util.ID

data class TodoModel(
    val id: ID?,
    val completed: Boolean?,
    val title: String?,
    val userId: ID?
)
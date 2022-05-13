package com.iddevops.common.domain.model

import com.iddevops.core.common.util.ID

interface TodoModel {
    val id: ID?
    val completed: Boolean?
    val title: String?
    val userId: ID?
}
package com.iddevops.common.domain.model

interface TodoModel {
    val id: Int?
    val completed: Boolean?
    val title: String?
    val userId: Int?
}
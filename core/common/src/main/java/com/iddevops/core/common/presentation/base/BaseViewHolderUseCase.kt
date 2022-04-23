package com.iddevops.core.common.presentation.base

interface BaseViewHolderUseCase<MODEL> {
    fun bind(data: MODEL)
}
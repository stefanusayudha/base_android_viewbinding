package com.iddevops.core.common

import com.iddevops.core.common.data.commonDataModule
import org.koin.core.module.Module

val coreCommonModules: List<Module> = listOf(
    commonDataModule
)
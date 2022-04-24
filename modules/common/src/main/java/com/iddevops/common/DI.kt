package com.iddevops.common

import com.iddevops.common.data.dataModule
import com.iddevops.common.domain.domainModule
import com.iddevops.common.presentation.presentationModule

val commonModules = listOf(
    dataModule, domainModule, presentationModule
)
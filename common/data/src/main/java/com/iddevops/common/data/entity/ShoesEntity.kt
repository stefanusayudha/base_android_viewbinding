package com.iddevops.common.data.entity

import com.iddevops.common.domain.model.ShoesModel

data class ShoesEntity(
    override val name: String?,
    override val imageUrl: String?,
    override val size: String?,
    override val color: String?
) : ShoesModel
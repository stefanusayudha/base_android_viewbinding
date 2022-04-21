package com.iddevops.common.data.web.self

import com.iddevops.common.data.entity.ShoesEntity

interface ProductApiUseCase {
    fun getProduct(): List<ShoesEntity>
}
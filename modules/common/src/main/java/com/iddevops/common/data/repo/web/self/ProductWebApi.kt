package com.iddevops.common.data.repo.web.self

import com.iddevops.common.data.entity.ShoesEntity
import com.iddevops.core.common.data.contract.WebService

interface ProductWebApi: WebService {
    fun getProduct(): List<ShoesEntity>
}
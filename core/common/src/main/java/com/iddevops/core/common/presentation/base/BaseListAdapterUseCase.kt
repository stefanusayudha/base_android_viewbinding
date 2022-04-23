package com.iddevops.core.common.presentation.base

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface BaseListAdapterUseCase<MODEL, I : ViewBinding, LI : ViewBinding> {

    fun itemBinding(parent: ViewGroup): I
    fun loadingItemBinding(parent: ViewGroup): LI
    fun onBindData(data: MODEL, binding: I)
    fun setBorderSize(size: Int, spanCount: Int = 1)
    fun requestLoadMore()
    fun showLoadMore()
    fun canLoadMore(boolean: Boolean)
}

package com.iddevops.core.common.presentation.base

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface BaseListAdapterUseCase<MODEL, I : ViewBinding> {
    fun itemBinding(parent: ViewGroup): I
    fun loadingItemBinding(parent: ViewGroup): ViewBinding
    fun onBindData(data: MODEL, binding: I)
    fun setBorderSize(size: Int)
    fun setSpanCount(spanCount: Int = 1)
    fun requestLoadMore()
    fun showLoadMore()
    fun canLoadMore(boolean: Boolean)
}

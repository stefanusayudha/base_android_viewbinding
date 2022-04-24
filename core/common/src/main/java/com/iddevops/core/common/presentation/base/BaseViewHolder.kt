package com.iddevops.core.common.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<MODEL, I : ViewBinding>(
    val binding: I? = null,
    val loadingBinding: ViewBinding? = null,
    val bindData: ((MODEL, I) -> Unit)?
) :
    RecyclerView.ViewHolder(
        binding?.root ?: loadingBinding?.root
        ?: throw NullPointerException("Error inflating layout")
    ), BaseViewHolderUseCase<MODEL> {
    var data: MODEL? = null

    override fun bind(data: MODEL) {
        this.data = data
        if (binding is I) bindData?.invoke(data, binding)
    }
}
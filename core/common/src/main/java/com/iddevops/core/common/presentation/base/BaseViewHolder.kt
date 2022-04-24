package com.iddevops.core.common.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base view holder
 * @author stefanus.ayudha@gmail.com
 * @param MODEL Data Model
 * @param I ViewBinding of item in recycle view
 * @property binding Item ViewBinding
 * @property loadingBinding Loading Item View Binding
 * @property bindData Callback to bind data to item binding
 * @constructor Create empty Base view holder
 */
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
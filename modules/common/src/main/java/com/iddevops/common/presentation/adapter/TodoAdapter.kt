package com.iddevops.common.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import com.iddevops.common.databinding.LayoutTodoListItemBinding
import com.iddevops.common.databinding.LayoutTodoListLoadingBinding
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.presentation.ext.BaseListAdapter
import com.iddevops.core.common.presentation.ext.ViewHolder

class TodoAdapter(
    val onRequestLoadMore: () -> Unit
) :
    BaseListAdapter<TodoModel, LayoutTodoListItemBinding, LayoutTodoListLoadingBinding>(TodoDiff) {

    override fun itemBinding(parent: ViewGroup): LayoutTodoListItemBinding {
        return LayoutTodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun loadingItemBinding(parent: ViewGroup): LayoutTodoListLoadingBinding {
        return LayoutTodoListLoadingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    private var snackbar: Snackbar? = null

    override fun onBindData(data: TodoModel, binding: LayoutTodoListItemBinding) {
        with(binding) {
            imgContent.setBackgroundColor(
                if (data.completed == true) Color.GREEN
                else Color.RED
            )

            tvTitle.text = data.title
            tvUserId.text = data.userId.toString()
            tvComplete.text = data.completed.toString()

            root.setOnClickListener {
                Toast.makeText(root.context, "${data.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var context: Context? = null
    private val dp16 by lazy {
        context?.resources?.getDimension(com.iddevops.core.ui.R.dimen.dp16)?.toInt() ?: 0
    }

    override fun onItemAttached(holder: ViewHolder<TodoModel, LayoutTodoListItemBinding, LayoutTodoListLoadingBinding>) {

        // initiate context
        context ?: run { context = holder.binding?.root?.context }

        context?.run {
            val topMargin = if (holder.data == currentList.first()) dp16 else 0
            holder.binding?.root?.layoutParams?.apply {
                this as ViewGroup.MarginLayoutParams
                setMargins(dp16, topMargin, dp16, dp16)
            }
            holder.loadingBinding?.root?.layoutParams?.apply {
                this as ViewGroup.MarginLayoutParams
                setMargins(dp16, topMargin, dp16, dp16)
            }
        }

        super.onItemAttached(holder)
    }

    override fun requestLoadMore() {
        onRequestLoadMore.invoke()
    }
}

object TodoDiff : DiffUtil.ItemCallback<TodoModel>() {
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        // fixme : better using date updated
        return oldItem.completed == newItem.completed
    }
}
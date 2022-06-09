package com.iddevops.common.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.iddevops.common.databinding.LayoutTodoListItemBinding
import com.iddevops.common.databinding.LayoutTodoListLoadingBinding
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.presentation.base.BaseAdapter

class TodoAdapter(
    val context: Context,
    val onRequestLoadMore: () -> Unit
) : BaseAdapter<TodoModel, LayoutTodoListItemBinding>(TodoDiff) {

    init {
        setBorderSize(context.resources.getDimension(com.iddevops.core.ui.R.dimen.dp16).toInt())
        // how many row do you want declare here
        setSpanCount(1)
    }

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

    override fun onBindData(data: TodoModel, binding: LayoutTodoListItemBinding) {
        with(binding) {
            with(imgContent) {
                setBackgroundColor(
                    if (data.completed == true) Color.GREEN
                    else Color.RED
                )
            }
            with(tvTitle) {
                text = data.title
            }
            with(tvUserId) {
                text = data.userId.toString()
            }
            with(tvComplete) {
                text = data.completed.toString()
            }
            root.setOnClickListener {
                Toast.makeText(root.context, "${data.title}", Toast.LENGTH_SHORT).show()
            }
        }
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
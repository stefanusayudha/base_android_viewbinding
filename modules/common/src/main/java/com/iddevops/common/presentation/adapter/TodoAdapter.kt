package com.iddevops.common.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iddevops.common.databinding.LayoutTodoListItemBinding
import com.iddevops.common.domain.model.TodoModel

class TodoAdapter(
    private val onClick: (TodoModel) -> Unit
) : ListAdapter<TodoModel, ViewHolder>(TodoDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutTodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        with(holder.binding.root) {
            val margin16 =
                this.context.resources.getDimension(com.iddevops.core.ui.R.dimen.dp16).toInt()
            val marginTop = if (holder.data == currentList.first()) margin16 else 0

            with(layoutParams as ViewGroup.MarginLayoutParams) {
                setMargins(margin16, marginTop, margin16, margin16)
            }
        }
        super.onViewAttachedToWindow(holder)
    }
}

class ViewHolder(
    val binding: LayoutTodoListItemBinding,
    val onClick: (TodoModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    var data: TodoModel? = null

    init {
        itemView.setOnClickListener {
            data?.let {
                onClick(it)
            }
        }
    }

    fun bind(todo: TodoModel) {
        data = todo

        with(binding) {
            imgContent.setBackgroundColor(
                if (todo.completed == true) Color.GREEN
                else Color.RED
            )

            tvTitle.text = todo.title
            tvUserId.text = todo.userId.toString()
            tvComplete.text = todo.completed.toString()
        }
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
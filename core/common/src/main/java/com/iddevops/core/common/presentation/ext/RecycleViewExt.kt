package com.iddevops.core.common.presentation.ext

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<M, I : ViewBinding, LI : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, ViewHolder<M, I, LI>>(diffCallback) {

    abstract fun itemBinding(parent: ViewGroup): I
    abstract fun loadingItemBinding(parent: ViewGroup): LI
    abstract fun onBindData(data: M, binding: I)
    open fun onItemAttached(holder: (ViewHolder<M, I, LI>)) {}
    open fun requestLoadMore() {}

    private var canLoadMore = true

    enum class VIEW_TYPE(val type: Int) {
        CONTENT(0), LOAD_MORE(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<M, I, LI> {
        return when (viewType) {
            VIEW_TYPE.CONTENT.type -> {
                ViewHolder(
                    binding = itemBinding(parent),
                    bindData = { d, b -> onBindData(d, b) }
                )
            }

            else -> {
                ViewHolder(
                    loadingBinding = loadingItemBinding(parent),
                    bindData = null
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE.LOAD_MORE.type else VIEW_TYPE.CONTENT.type
    }

    override fun onBindViewHolder(holder: ViewHolder<M, I, LI>, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder<M, I, LI>) {
        onItemAttached(holder)
        super.onViewAttachedToWindow(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!canLoadMore) return

                val lManager = recyclerView.layoutManager as LinearLayoutManager?
                val lastVisibleItem = lManager?.findLastVisibleItemPosition()

                if (lastVisibleItem != null
                    && currentList.isNotEmpty()
                    && currentList.last() != null
                    && lastVisibleItem > currentList.size - 5
                ) {
                    requestLoadMore()
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun showLoadMore() {
        when {
            currentList.isEmpty() -> {
                submitList(listOf(null))
            }
            currentList.last() != null -> {
                val newData = arrayListOf<M?>().apply {
                    addAll(currentList)

                    // this will draw as load more object
                    add(null)
                }

                submitList(newData)
            }
        }
    }

    fun canLoadMore(boolean: Boolean) {
        canLoadMore = boolean
    }
}

class ViewHolder<M, I : ViewBinding, LI : ViewBinding>(
    val binding: I? = null,
    val loadingBinding: LI? = null,
    val bindData: ((M, I) -> Unit)?
) :
    RecyclerView.ViewHolder(
        binding?.root ?: loadingBinding?.root
        ?: throw NullPointerException("Error inflating layout")
    ) {
    var data: M? = null

    fun bind(data: M) {
        this.data = data
        if (binding is I) bindData?.invoke(data, binding)
    }
}
package com.iddevops.core.common.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Base adapter
 *
 * @author stefanus.ayudha@gmail.com
 * @param MODEL Data Model
 * @param I Item Binding
 * @constructor Require differ configuration
 *
 * @param diffCallback Differ Configuration
 */

// Note: to use this adapter use GridLayout manager
abstract class BaseAdapter<MODEL, I : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<MODEL>
) : ListAdapter<MODEL, BaseViewHolder<MODEL, I>>(diffCallback),
    BaseListAdapterUseCase<MODEL, I> {

    abstract override fun itemBinding(parent: ViewGroup): I
    abstract override fun loadingItemBinding(parent: ViewGroup): ViewBinding
    abstract override fun onBindData(data: MODEL, binding: I)
    override fun requestLoadMore() {}

    private var canLoadMore = true
    private var borderSize: Int = 0
    private var spanCount = MutableStateFlow(1)

    enum class VIEW_TYPE(val type: Int) {
        CONTENT(0), LOAD_MORE(1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MODEL, I> {
        return when (viewType) {
            VIEW_TYPE.CONTENT.type -> {
                BaseViewHolder(
                    binding = itemBinding(parent),
                    bindData = { d, b -> onBindData(d, b) }
                )
            }

            else -> {
                BaseViewHolder(
                    loadingBinding = loadingItemBinding(parent),
                    bindData = null
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE.LOAD_MORE.type else VIEW_TYPE.CONTENT.type
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MODEL, I>, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<MODEL, I>) {
        val _topMargin = when {
            currentList.size >= spanCount.value
                    && currentList.subList(0, spanCount.value).contains(holder.data) -> borderSize
            else -> 0
        }

        val _rightMargin = when {
            (currentList.indexOf(holder.data) + 1) % spanCount.value == 0 -> borderSize
            else -> borderSize / 2
        }

        val _leftMargin = when {
            (currentList.indexOf(holder.data) + 1) % spanCount.value == 1 -> borderSize
            else -> borderSize / 2
        }

        holder.binding?.root?.layoutParams?.apply {
            this as ViewGroup.MarginLayoutParams
            setMargins(_leftMargin, _topMargin, _rightMargin, borderSize)
        }

        holder.loadingBinding?.root?.layoutParams?.apply {
            this as ViewGroup.MarginLayoutParams
            setMargins(_leftMargin, _topMargin, _rightMargin, borderSize)
        }

        super.onViewAttachedToWindow(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!canLoadMore) return

                val lastVisibleItem = recyclerView.layoutManager?.let { rvm ->
                    (recyclerView.layoutManager as GridLayoutManager?)?.findLastVisibleItemPosition()
                }

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

        CoroutineScope(Dispatchers.Main).launch {
            spanCount.collect { count ->
                (recyclerView.layoutManager as GridLayoutManager).spanCount = count
                notifyDataSetChanged()
            }
        }
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun setBorderSize(size: Int) {
        borderSize = size
    }

    override fun setSpanCount(spanCount: Int) {
        this.spanCount.value = spanCount
    }

    override fun showLoadMore() {
        when {
            currentList.isEmpty() -> {
                submitList(listOf(null))
            }
            currentList.last() != null -> {
                val newData = arrayListOf<MODEL?>().apply {
                    addAll(currentList)

                    // this will draw as load more object
                    add(null)
                }

                submitList(newData)
            }
        }
    }

    override fun canLoadMore(boolean: Boolean) {
        canLoadMore = boolean
    }
}
package com.iddevops.core.common.presentation.base

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

// FIXME: this adapter support span count up to 2 rows only
abstract class BaseAdapter<MODEL, I : ViewBinding, LI : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<MODEL>
) : ListAdapter<MODEL, BaseViewHolder<MODEL, I, LI>>(diffCallback),
    BaseListAdapterUseCase<MODEL, I, LI> {

    abstract override fun itemBinding(parent: ViewGroup): I
    abstract override fun loadingItemBinding(parent: ViewGroup): LI
    abstract override fun onBindData(data: MODEL, binding: I)
    override fun requestLoadMore() {}

    private var resourceContext: Context? = null
    private var canLoadMore = true
    private var borderSize: Int = 0
    private var spanCount: Int = 1

    enum class VIEW_TYPE(val type: Int) {
        CONTENT(0), LOAD_MORE(1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MODEL, I, LI> {
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

    override fun onBindViewHolder(holder: BaseViewHolder<MODEL, I, LI>, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<MODEL, I, LI>) {
        val _topMargin = when {
            currentList.size >= spanCount
                    && currentList.subList(0, spanCount).contains(holder.data) -> borderSize
            else -> 0
        }

        val _rightMargin = when {
            (currentList.indexOf(holder.data) + 1) % spanCount == 0 -> borderSize
            else -> borderSize/2
        }

        val _leftMargin = when {
            (currentList.indexOf(holder.data) + 1) % spanCount == 1 -> borderSize
            else -> borderSize/2
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
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun setBorderSize(size: Int, spanCount: Int) {

        if (spanCount > 2)
            Log.w(
                this::class.simpleName,
                "setBorderSize: this feature currently support only up to 2 row span count"
            )

        borderSize = size
        this.spanCount = spanCount
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
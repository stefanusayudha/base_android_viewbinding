package com.iddevops.common.presentation.fragment.todolist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.iddevops.common.databinding.FragmentTodoListBinding
import com.iddevops.common.presentation.adapter.TodoAdapter
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding>() {

    private val vm: TodoListViewModelUseCase by viewModel()
    private var todoAdapter: TodoAdapter? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentTodoListBinding {
        return FragmentTodoListBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        if (vm.listTodos.value !is RequestState.Success)
            vm.getCacheTodos()
    }

    override fun initUI() {
        todoAdapter = TodoAdapter(
            requireContext(),
            onRequestLoadMore = {
                vm.loadMoreTodos()
            }
        )
        binding.rvContent.adapter = todoAdapter
    }

    override fun initAction() {
        Handler(Looper.getMainLooper()).postDelayed({ todoAdapter?.setSpanCount(1) }, 7000)
        Handler(Looper.getMainLooper()).postDelayed({ todoAdapter?.setSpanCount(2) }, 9000)
        Handler(Looper.getMainLooper()).postDelayed({ todoAdapter?.setSpanCount(3) }, 11000)
    }

    override fun initObserver() {

        lifecycleScope.launch {
            vm.cacheTodos.collect {
                when (it) {
                    is RequestState.Default -> {}
                    is RequestState.Loading -> {
                        todoAdapter?.showLoadMore()
                    }
                    is RequestState.Success -> {
                        if (it.data.isEmpty())
                            vm.getTodos()
                        else
                            todoAdapter?.submitList(it.data)
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            vm.listTodos.collect {
                when (it) {
                    is RequestState.Default -> {

                    }
                    is RequestState.Failed -> {
                        Toast.makeText(
                            this@TodoListFragment.requireContext(),
                            "Failed to load list",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is RequestState.Loading -> {
                        todoAdapter?.showLoadMore()
                    }
                    is RequestState.Success -> {
                        todoAdapter?.submitList(it.data)
                    }
                }
            }
        }

        lifecycleScope.launch {
            vm.canLoadMore.collect { canLoadMore ->
                todoAdapter?.canLoadMore(canLoadMore)
            }
        }
    }
}
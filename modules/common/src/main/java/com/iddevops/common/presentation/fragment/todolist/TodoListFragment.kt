package com.iddevops.common.presentation.fragment.todolist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.iddevops.common.databinding.FragmentTodoListBinding
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.common.presentation.adapter.TodoAdapter
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseFragment
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding>() {

    private val vm: TodoListViewModel by viewModel()
    private var todoAdapter: TodoAdapter? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentTodoListBinding {
        return FragmentTodoListBinding.inflate(inflater, container, false)
    }

    override fun initNavigation() {

    }

    override fun initData() {
        with(vm) {
            if (listTodos.value !is RequestState.Success)
                getCacheTodos()
        }
    }

    override fun initUI() {
        todoAdapter = TodoAdapter(
            requireContext(),
            onRequestLoadMore = {
                with(vm) {
                    getMoreTodos()
                }
            }
        )
        with(binding) {
            with(rvContent) {
                adapter = todoAdapter
            }
        }
    }

    override fun initAction() {
        // uncomment bellow to preview span count modifier
//        with(Handler(Looper.getMainLooper())) {
//            postDelayed({ todoAdapter?.setSpanCount(1) }, 7000)
//            postDelayed({ todoAdapter?.setSpanCount(2) }, 9000)
//            postDelayed({ todoAdapter?.setSpanCount(3) }, 11000)
//        }
    }

    override fun initObserver() {
        with(lifecycleScope) {
            launch { vm.cacheTodos.collect(cacheTodosRenderEngine) }
            launch { vm.listTodos.collect(todoListRenderEngine) }
            launch {
                vm.canLoadMore.collect { canLoadMore ->
                    todoAdapter?.canLoadMore(canLoadMore)
                }
            }
        }
    }

    private val cacheTodosRenderEngine = FlowCollector<RequestState<List<TodoModel>>> {
        when (it) {
            is RequestState.Default -> {}
            is RequestState.Loading -> {
                with(todoAdapter) {
                    this?.showLoadMore()
                }
            }
            is RequestState.Success -> {
                if (it.data.isEmpty())
                    with(vm) {
                        getTodos()
                    }
                else
                    with(todoAdapter) {
                        this?.submitList(it.data)
                    }
            }
            is RequestState.Failed -> {
                with(vm) {
                    getTodos()
                }
            }
            else -> {

            }
        }
    }
    private val todoListRenderEngine = FlowCollector<RequestState<List<TodoModel>>> {
        when (it) {
            is RequestState.Default -> {}
            is RequestState.Failed -> {
                Toast.makeText(
                    this@TodoListFragment.requireContext(),
                    "Failed to load list",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is RequestState.Loading -> {
                with(todoAdapter) {
                    this?.showLoadMore()
                }
            }
            is RequestState.Success -> {
                with(todoAdapter) {
                    this?.submitList(it.data)
                }
            }
        }
    }
}
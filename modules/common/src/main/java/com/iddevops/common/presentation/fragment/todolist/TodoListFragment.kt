package com.iddevops.common.presentation.fragment.todolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.iddevops.common.databinding.FragmentTodoListBinding
import com.iddevops.common.presentation.adapter.TodoAdapter
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseFragment
import com.iddevops.core.common.presentation.ext.gone
import com.iddevops.core.common.presentation.ext.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
            vm.getTodos()
    }

    override fun initUi() {
        todoAdapter = TodoAdapter {
            Log.d("asdadad", "initUi: ${it.title}")
        }
        binding.rvContent.adapter = todoAdapter
    }

    override fun initObserver() {
        CoroutineScope(Dispatchers.Main).launch {
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
                        binding.progressBar.visible()
                    }
                    is RequestState.Success -> {
                        todoAdapter?.submitList(it.data)
                        binding.progressBar.gone()
                    }
                }
            }
        }
    }
}
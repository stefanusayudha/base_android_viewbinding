package com.iddevops.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.yummy.core.common.util.base.BaseView

abstract class BaseFragment<V : ViewBinding> : Fragment(), BaseView {

    private lateinit var binding: V
    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container, savedInstanceState)
        initData()
        initUi()
        initObserver()
        return binding.root
    }
}
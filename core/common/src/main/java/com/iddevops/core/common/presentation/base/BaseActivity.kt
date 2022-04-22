package com.iddevops.core.common.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding>: AppCompatActivity(), BaseView {

    protected lateinit var binding: V
    abstract fun getViewBinding(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initUI()
        initAction()
        initObserver()
        binding = getViewBinding()
        setContentView(binding.root)
    }
}
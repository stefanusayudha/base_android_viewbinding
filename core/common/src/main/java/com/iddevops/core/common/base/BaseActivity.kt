package com.iddevops.core.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yummy.core.common.util.base.BaseView

abstract class BaseActivity<V: ViewBinding>: AppCompatActivity(), BaseView {

    private lateinit var binding: V
    abstract fun getBinding(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initUi()
        initObserver()
        binding = getBinding()
        setContentView(binding.root)
    }
}
package com.iddevops.core.common.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base activity
 * @author stefanus.ayudha@gmail.com
 * @param V View Binding to use within the activity
 * @constructor Create empty Base activity
 */
abstract class BaseActivity<V: ViewBinding>: AppCompatActivity(), BaseView {

    protected lateinit var binding: V
    abstract fun getViewBinding(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
        initData()
        initUI()
        initAction()
        initObserver()
        binding = getViewBinding()
        setContentView(binding.root)
    }
}
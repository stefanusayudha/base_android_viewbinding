package com.iddevops.core.common.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base fragment
 * @author stefanus.ayudha@gmail.com
 * @param V View Binding to use within the fragment
 * @constructor Create empty Base fragment
 */
abstract class BaseFragment<V : ViewBinding> : Fragment(), BaseView {

    lateinit var binding: V
    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container, savedInstanceState)
        initNavigation()
        initData()
        initUI()
        initAction()
        initObserver()
        return binding.root
    }
}
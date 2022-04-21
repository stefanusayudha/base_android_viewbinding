package com.iddevops.common.feature_dashboard.fragment.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iddevops.common.domain.model.ShoesModel
import com.iddevops.common.feature_dashboard.databinding.FragmentSampleBinding
import com.iddevops.core.common.request.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : Fragment() {

    private lateinit var binding: FragmentSampleBinding
    private val vm: SampleViewModelUseCase by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSampleBinding.inflate(layoutInflater, container, false)

        initData()
        initObserver()

        return binding.root
    }

    private fun initData() {
        vm.getProductList()
    }

    private fun initObserver() {
        CoroutineScope(Dispatchers.Main).launch {
            vm.listProduct.collect {
                displayState(it)
            }
        }
    }

    private fun displayState(state: RequestState<List<ShoesModel>>) {
        binding.tvInfo.text = when (state) {
            is RequestState.Default -> "State Default"
            is RequestState.Failed -> "State Failed: ${state.e?.localizedMessage}"
            is RequestState.Loading -> "State Loading"
            is RequestState.Success -> "State Success: \n\n${state.data}"
        }
    }
}
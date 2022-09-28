package com.kieronquinn.app.taptap.ui.screens.settings.lowpower.shizukuinfo

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kieronquinn.app.taptap.databinding.FragmentSettingsLowPowerModeShizukuInfoBinding
import com.kieronquinn.app.taptap.ui.base.BackAvailable
import com.kieronquinn.app.taptap.ui.base.BoundFragment
import com.kieronquinn.app.taptap.utils.extensions.applyBottomInsets
import com.kieronquinn.app.taptap.utils.extensions.onClicked
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsLowPowerModeShizukuInfoFragment: BoundFragment<FragmentSettingsLowPowerModeShizukuInfoBinding>(FragmentSettingsLowPowerModeShizukuInfoBinding::inflate), BackAvailable {

    private val viewModel by viewModel<SettingsLowPowerModeShizukuInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMonet()
        setupShizukuButton()
        setupSuiButton()
        setupScrollView()
    }

    private fun setupMonet() {
        val cardBackground = ColorStateList.valueOf(monet.getPrimaryColor(requireContext()))
        binding.settingsLowPowerModeShizukuInfoErrorCardSui.backgroundTintList = cardBackground
        binding.settingsLowPowerModeShizukuInfoErrorCardShizuku.backgroundTintList = cardBackground
    }

    private fun setupShizukuButton() = viewLifecycleOwner.lifecycleScope.launchWhenResumed {
        binding.settingsLowPowerModeShizukuInfoErrorCardShizukuButton.onClicked().collect {
            viewModel.onShizukuClicked(requireContext())
        }
    }

    private fun setupScrollView() = with(binding.settingsLowPowerModeShizukuInfoError) {
        applyBottomInsets(binding.root)
    }

    private fun setupSuiButton() = viewLifecycleOwner.lifecycleScope.launchWhenResumed {
        binding.settingsLowPowerModeShizukuInfoErrorCardSuiButton.onClicked().collect {
            viewModel.onSuiClicked(requireContext())
        }
    }

}
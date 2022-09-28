package com.kieronquinn.app.taptap.components.columbus.gates.custom

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.kieronquinn.app.taptap.components.accessibility.TapTapAccessibilityRouter
import com.kieronquinn.app.taptap.components.columbus.gates.TapTapGate
import com.kieronquinn.app.taptap.utils.extensions.isPackageCamera
import kotlinx.coroutines.flow.*
import org.koin.core.component.inject

class CameraVisibilityGate(
    serviceLifecycle: Lifecycle,
    context: Context
) : TapTapGate(serviceLifecycle, context) {

    private val accessibilityRouter by inject<TapTapAccessibilityRouter>()

    private val cameraOpen = accessibilityRouter.accessibilityOutputBus.filter { it is TapTapAccessibilityRouter.AccessibilityOutput.AppOpen }
        .map { context.isPackageCamera((it as TapTapAccessibilityRouter.AccessibilityOutput.AppOpen).packageName) }
        .stateIn(lifecycleScope, SharingStarted.Eagerly, false)

    init {
        lifecycleScope.launchWhenCreated {
            cameraOpen.collect {
                notifyListeners()
            }
        }
    }
    
    override fun isBlocked(): Boolean {
        showAccessibilityNotificationIfNeeded()
        return cameraOpen.value
    }

}
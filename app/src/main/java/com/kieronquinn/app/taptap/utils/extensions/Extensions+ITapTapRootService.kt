package com.kieronquinn.app.taptap.utils.extensions

import android.annotation.SuppressLint
import android.app.IApplicationThread
import android.content.Context
import android.content.Intent
import com.kieronquinn.app.taptap.models.service.ActivityContainer
import com.kieronquinn.app.taptap.root.ITapTapRootService

/**
 *  Start an activity of [intent] as root.
 */
@SuppressLint("PrivateApi")
fun ITapTapRootService.startActivity(context: Context, intent: Intent, omitThread: Boolean = false, enterResId: Int? = null, exitResId: Int? = null): Int {
    val applicationThread = Context::class.java.getMethod("getIApplicationThread").invoke(context) as IApplicationThread
    return startActivityPrivileged(ActivityContainer(if(omitThread) null else applicationThread, enterResId, exitResId), intent)
}
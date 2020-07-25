package com.neillon.router

import android.content.Context
import android.content.Intent
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

infix fun Context.startFeature(feature: Feature) {
    val splitInstallManager = SplitInstallManagerFactory.create(this)
    val request = SplitInstallRequest.newBuilder()
        .addModule(feature.name)
        .build()

    koinApplication { unloadKoinModules(feature.modules) }
    koinApplication { loadKoinModules(feature.modules) }

    splitInstallManager.startInstall(request)
    if (splitInstallManager.installedModules.contains(feature.name)) {
        val intent = Intent().apply {
            setClassName(this@startFeature, feature.entryPointClassName)
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(intent)
    }
}
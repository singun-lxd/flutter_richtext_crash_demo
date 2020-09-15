package com.demo.flutter_app2

import android.app.AlertDialog
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import io.flutter.embedding.android.FlutterActivity
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityManager
import android.accessibilityservice.AccessibilityService
import android.content.pm.ServiceInfo


class MainActivity: FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showAlertDialog()
    }

    private fun showAlertDialog() {
        if (!isAccessibilityServiceEnabled(this, MyAccessibilityService::class.java)) {
            val service = getString(R.string.service_name)
            val message = getString(R.string.notice_enable, service)
            AlertDialog.Builder(this)
                    .setTitle(service)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        openAccessibility()
                        dialog.dismiss()
                    }
                    .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
        }
    }

     private fun isAccessibilityServiceEnabled(context: Context, service: Class<out AccessibilityService?>): Boolean {
        val am = context.getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        for (enabledService in enabledServices) {
            val enabledServiceInfo: ServiceInfo = enabledService.resolveInfo.serviceInfo
            if (enabledServiceInfo.packageName == context.packageName && enabledServiceInfo.name == service.name) return true
        }
        return false
    }

    private fun openAccessibility() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }
}

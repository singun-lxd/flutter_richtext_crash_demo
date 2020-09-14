package com.demo.flutter_app2

import android.app.AlertDialog
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils.SimpleStringSplitter
import android.util.Log
import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {
    companion object {
        const val TAG = "MainActivity"

        const val SERVICE_NAME = "MyAccessibilityService"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isAccessibilitySettingsOn(SERVICE_NAME, this)) {
            AlertDialog.Builder(this)
                        .setTitle(R.string.service_desc)
                        .setMessage(R.string.notice_enable)
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            openAccessibility(SERVICE_NAME, this)
                        }
                        .create()
                        .show()
        }
    }

    private fun isAccessibilitySettingsOn(accessibilityServiceName: String, context: Context): Boolean {
      var accessibilityEnable = 0
      val serviceName = context.packageName + "/" + accessibilityServiceName
      try {
          accessibilityEnable = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, 0)
      } catch (e: Exception) {
          Log.e(TAG, "get accessibility enable failed, the err:" + e.message)
      }
      if (accessibilityEnable == 1) {
          val stringColonSplitter = SimpleStringSplitter(':')
          val settingValue: String? = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
          if (settingValue != null) {
              stringColonSplitter.setString(settingValue)
              while (stringColonSplitter.hasNext()) {
                  val accessibilityService = stringColonSplitter.next()
                  if (accessibilityService.equals(serviceName, ignoreCase = true)) {
                      Log.v(TAG, "We've found the correct setting - accessibility is switched on!")
                      return true
                  }
              }
          }
      } else {
          Log.d(TAG, "Accessibility service disable")
      }
      return false
    }

    private fun openAccessibility(accessibilityServiceName: String, context: Context) {
      if (!isAccessibilitySettingsOn(accessibilityServiceName, context)) {
          val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
          startActivity(intent)
      }
    }
}

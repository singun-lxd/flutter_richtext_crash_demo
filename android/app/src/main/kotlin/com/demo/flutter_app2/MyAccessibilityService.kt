package com.demo.flutter_app2

import android.app.Service
import android.content.Intent
import android.os.IBinder

 /**
 * Created by singun on 2020/9/14.
 */
class MyAccessibilityService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
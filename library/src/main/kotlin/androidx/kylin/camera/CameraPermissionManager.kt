package androidx.kylin.camera

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * 相机权限管理
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
object CameraPermissionManager {

    /**
     * 检查是否有相机权限
     */
    fun hasPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) or ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.RECORD_AUDIO
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    /**
     * 申请相机权限
     */
    fun requestCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO),
            10240
        )
    }

    /**
     * 处理权限返回结果
     */
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray, block: (Boolean) -> Unit) {
        if (requestCode == 10240) {
            var granted = false
            grantResults.forEach {
                if (it == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    granted = true
                }
            }
            block(granted)
        }
    }
}
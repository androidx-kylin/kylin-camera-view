package androidx.kylin.camera.core

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.kylin.camera.model.CameraOptions

/**
 * CameraContext
 * @author rae
 * @copyright https://github.com/raedev
 */
class CameraContext internal constructor(val context: Context) {

    private val cameraPermissions =
        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)

    var options: CameraOptions = CameraOptions()
        set(value) {
            field = value
            onCameraOptionsChanged()
        }

    /**
     * When the camera options changed, this context will be notified.
     * */
    private fun onCameraOptionsChanged() {

    }

    // region Camera Permission Manager

    /**
     * Whether you have been granted the camera permission.
     */
    fun hasPermissions(context: Context): Boolean {
        return cameraPermissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Request the camera permissions.
     */
    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, cameraPermissions, 10240)
    }

    /**
     * When the activity onRequestPermissionsResult callback, you should call this method to handle permission result.
     */
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray, block: (Boolean) -> Unit) {
        block(requestCode == 10240 && grantResults.all { it == android.content.pm.PackageManager.PERMISSION_GRANTED })
    }

    // endregion
}
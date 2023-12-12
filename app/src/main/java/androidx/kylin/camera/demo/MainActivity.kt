package androidx.kylin.camera.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.kylin.camera.model.CameraErrorCode
import androidx.kylin.camera.CameraListener
import androidx.kylin.camera.CameraPermissionManager
import androidx.kylin.camera.demo.databinding.ActivityMainBinding
import androidx.kylin.camera.view.CameraView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cameraView.addCameraListener(object : CameraListener {
            override fun onCameraError(view: CameraView, code: CameraErrorCode, message: String) {
                super.onCameraError(view, code, message)
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })

        if (CameraPermissionManager.hasPermission(this)) {
            // 开始预览
            binding.cameraView.startCamera(this)
        } else {
            CameraPermissionManager.requestCameraPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        CameraPermissionManager.handlePermissionResult(requestCode, grantResults) { granted ->
            if (granted) binding.cameraView.startCamera(this) else Toast.makeText(
                this,
                "No Permission!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
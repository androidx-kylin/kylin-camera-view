package androidx.kylin.camera.view

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.kylin.camera.CameraController
import androidx.kylin.camera.model.CameraErrorCode
import androidx.kylin.camera.model.CameraLensFacing
import androidx.kylin.camera.CameraListener
import androidx.kylin.camera.model.CameraOptions
import androidx.kylin.camera.CameraPermissionManager
import androidx.lifecycle.LifecycleOwner

/**
 * render camera content
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
class CameraView : FrameLayout {

    internal companion object {
        private val TAG: String = "CameraView"

        internal fun log(msg: String) {
            Log.d(TAG, msg)
        }

        internal fun error(msg: String) {
            Log.e(TAG, msg)
        }
    }

    // region 属性定义

    /** 相机主入口 */
    private lateinit var preview: PreviewView

    /** 相机回调 */
    private var cameraListeners: MutableList<CameraListener> = mutableListOf()

    /** 相机选项 */
    private var options: CameraOptions = CameraOptions()

    /** 相机捕获 */
    private var capture: ImageCapture = ImageCapture.Builder().build()

    lateinit var controller: CameraController
        private set


    // endregion

    // region 构造函数
    constructor(context: android.content.Context) : super(context) {
        initView()
    }

    constructor(
        context: android.content.Context,
        attrs: android.util.AttributeSet?
    ) : super(context, attrs) {
        initView()
    }

    constructor(
        context: android.content.Context,
        attrs: android.util.AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView()
    }

    // endregion


    private fun initView() {
        preview = PreviewView(context, null)
        preview.setBackgroundColor(Color.BLACK)
        preview.fitsSystemWindows = false
        addView(
            preview,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        controller = CameraViewController()
    }

    /**
     * 添加相机监听
     */
    fun addCameraListener(listener: CameraListener) {
        if (!cameraListeners.contains(listener)) cameraListeners.add(listener)
    }

    /**
     * 通知相机发生错误
     */
    private fun notifyCameraError(code: CameraErrorCode, msg: String) =
        cameraListeners.forEach {
            it.onCameraError(this, code, msg)
            error(msg)
        }

    /**
     * 开始预览相机
     * @param owner LifecycleOwner 将相机绑定到生命周期
     */
    fun startCamera(owner: LifecycleOwner): Boolean {
        if (!CameraPermissionManager.hasPermission(context)) {
            notifyCameraError(CameraErrorCode.NoPermission, "No Camera permission!")
            return false
        }
        val future = ProcessCameraProvider.getInstance(context)
        future.addListener({
            runCatching {
                future.get().also { provider ->

                    // 构建相机预览
                    Preview.Builder().build().also {
                        val selector = when (options.facing) {
                            CameraLensFacing.FRONT -> CameraSelector.DEFAULT_FRONT_CAMERA
                            else -> CameraSelector.DEFAULT_BACK_CAMERA
                        }
                        it.setSurfaceProvider(preview.surfaceProvider)
                        runCatching { provider.unbindAll() }
                        provider.bindToLifecycle(owner, selector, it, this.capture)
                    }
                }
            }.onFailure {
                notifyCameraError(CameraErrorCode.Unknown, it.message!!)
            }
        }, ContextCompat.getMainExecutor(context))
        return true
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        if (child !is CameraControlView) {
            error("CameraView only support CameraControlView as child!")
            return
        }
        // Attach Controller
        child.mController = controller
    }

    /**
     * 相机控制器实现
     */
    private inner class CameraViewController : CameraController {

        override val options: CameraOptions
            get() = this@CameraViewController.options


        override fun toggleLensFacing(facing: CameraLensFacing?) {
        }

    }
}
package androidx.kylin.camera.view

import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.camera.view.PreviewView
import androidx.kylin.camera.core.CameraContext
import androidx.kylin.camera.core.CameraxController
import androidx.kylin.camera.core.InternalCameraController

/**
 * Camera view is a wrapper of [PreviewView].
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
class CameraView : FrameLayout {


    // region constructor
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

    private lateinit var preview: PreviewView
    lateinit var controller: CameraxController

    private fun initView() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        preview = PreviewView(context).apply {
            setBackgroundColor(Color.BLACK)
        }
        addView(preview, layoutParams)

        // init context and controller
        controller = InternalCameraController(CameraContext(context))
    }

}
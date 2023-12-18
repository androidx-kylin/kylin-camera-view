package androidx.kylin.camera.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.kylin.camera.core.CameraxController
import androidx.kylin.camera.event.UseCaseEvent
import androidx.lifecycle.LifecycleOwner

/**
 * CameraLayout is container of [CameraView] and [UseCaseView].
 * @author rae
 * @copyright https://github.com/raedev
 */
class CameraLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initView()
    }

    private lateinit var cameraView: CameraView

    internal val controller: CameraxController get() = cameraView.controller

    private fun initView() {
        cameraView = obtainCameraView()
    }

    private fun obtainCameraView(): CameraView {
        return children.find { it is CameraView } as CameraView? ?: CameraView(context).also {
            addView(it, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

    fun bind(owner: LifecycleOwner, callback: (UseCaseEvent) -> Unit) {
        // owner bind to camera context
        controller.registerUseCaseEvent(callback)
    }

}
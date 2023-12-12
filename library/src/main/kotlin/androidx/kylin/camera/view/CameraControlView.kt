package androidx.kylin.camera.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.kylin.camera.CameraController

/**
 * 相机控制视图，位于相机图层之上，父布局为FrameLayout。
 * 我们推荐继承CameraControlView来实现自己的相机控制视图。
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
abstract class CameraControlView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 相机控制器
     */
    internal var mController: CameraController? = null

    /**
     * 相机控制器
     */
    protected val controller: CameraController get() = mController!!


    /** 真正消费的高度由子视图确定 */
    open val actualHeight: Int get() = measuredHeight


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // 释放控制器
        mController = null
    }
}
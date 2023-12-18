package androidx.kylin.camera.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.kylin.camera.core.CameraxController

/**
 * UseCaseView is a render of view. When a use case event is triggered, it will be rendered in this
 * view. for example: capture button,record button,photo display of captured, etc.
 *
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
abstract class UseCaseView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    protected val controller: CameraxController get() = (this.parent as CameraLayout).controller

}
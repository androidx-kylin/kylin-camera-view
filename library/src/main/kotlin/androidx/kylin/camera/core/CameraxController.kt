package androidx.kylin.camera.core

import androidx.camera.view.LifecycleCameraController
import androidx.kylin.camera.event.UseCaseEvent

/**
 * The Camera controller provides some interface to help you operate the camera.
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
abstract class CameraxController(
    internal val cameraContext: CameraContext
) {



    fun registerUseCaseEvent(callback: (UseCaseEvent) -> Unit, filter: (UseCaseEvent) -> Boolean) {

    }

    // the source lifecycle camera controller.
    private val controller = LifecycleCameraController(cameraContext.context)

}
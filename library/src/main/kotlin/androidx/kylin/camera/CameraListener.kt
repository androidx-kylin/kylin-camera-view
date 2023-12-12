package androidx.kylin.camera

import androidx.kylin.camera.model.CameraErrorCode
import androidx.kylin.camera.view.CameraView

/**
 * 相机回调
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
interface CameraListener {

    /**
     * 相机发生错误
     */
    fun onCameraError(view: CameraView, code: CameraErrorCode, message: String) = Unit


}
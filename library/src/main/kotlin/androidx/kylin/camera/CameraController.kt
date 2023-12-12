package androidx.kylin.camera

import androidx.kylin.camera.model.CameraLensFacing
import androidx.kylin.camera.model.CameraOptions

/**
 * 相机控制器，提供接口方法对相机进行相关操作。
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
interface CameraController {

    /**
     * 当前相机参数
     */
    val options: CameraOptions

    /**
     * 切换摄像头
     * @param facing 摄像头方向，默认自动切换，若设置则切换到此状态。
     */
    fun toggleLensFacing(facing: CameraLensFacing? = null)

    /**
     * 执行拍照
     */
    suspend fun takePhoto()

}